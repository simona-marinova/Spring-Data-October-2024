package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ConstellationDTO;
import softuni.exam.models.entity.Constellation;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.service.ConstellationService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.ValidationUtilImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ConstellationServiceImpl implements ConstellationService {

    private static final String FILE_PATH = "C:\\Users\\stamb\\Desktop\\exams\\29.07.2023\\Find-a-Star Skeleton\\Find-a-Star Skeleton\\src\\main\\resources\\files\\json\\constellations.json";
    private final ConstellationRepository constellationRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
private final ValidationUtil validationUtil;
    
    @Autowired
    public ConstellationServiceImpl(ConstellationRepository constellationRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.constellationRepository = constellationRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.constellationRepository.count()>0;
    }

    @Override
    public String readConstellationsFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importConstellations() throws IOException {
        StringBuilder sb = new StringBuilder();
        ConstellationDTO[] constellationDTOS = this.gson.fromJson(readConstellationsFromFile(), ConstellationDTO[].class);
        for (ConstellationDTO constellationDTO : constellationDTOS) {
            if(!this.validationUtil.isValid(constellationDTO) ||
                    this.constellationRepository.findByName(constellationDTO.getName()).isPresent()) {
                sb.append("Invalid constellation").append(System.lineSeparator());
                continue;
            }

            Constellation constellation = this.modelMapper.map(constellationDTO, Constellation.class);
            this.constellationRepository.saveAndFlush(constellation);
            sb.append(String.format("Successfully imported constellation %s - %s", constellation.getName(), constellation.getDescription())).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
