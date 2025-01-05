package sofuni.exam.service.Impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sofuni.exam.models.dto.PlanetDTO;
import sofuni.exam.models.entity.Planet;
import sofuni.exam.models.enums.Type;
import sofuni.exam.repository.PlanetRepository;
import sofuni.exam.service.PlanetService;
import sofuni.exam.util.ValidationUtil;
import sofuni.exam.util.ValidationUtilImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PlanetServiceImpl implements PlanetService {
    private static final String FILE_PATH = "src/main/resources/files/json/planets.json";
    private final PlanetRepository planetRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;


    @Autowired
    public PlanetServiceImpl(PlanetRepository planetRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.planetRepository = planetRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return this.planetRepository.count()>0;
    }

    @Override
    public String readPlanetsFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importPlanets() throws IOException {
StringBuilder sb = new StringBuilder();
        PlanetDTO[] planetDTOS = this.gson.fromJson(readPlanetsFileContent(), PlanetDTO[].class);
        for (PlanetDTO planetDTO : planetDTOS) {
            if(planetRepository.findByName(planetDTO.getName()).isPresent() ||
                    !validationUtil.isValid(planetDTO)) {
sb.append("Invalid planet").append(System.lineSeparator());
continue;
            }
            Planet planet = this.modelMapper.map(planetDTO, Planet.class);
            planet.setType(Type.valueOf(planetDTO.getType()));
            planetRepository.saveAndFlush(planet);
            sb.append(String.format("Successfully imported planet %s", planetDTO.getName())).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
