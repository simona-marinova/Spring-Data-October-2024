package sofuni.exam.service.Impl;

import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sofuni.exam.models.dto.MoonDTO;
import sofuni.exam.models.dto.MoonsDTO;
import sofuni.exam.models.entity.Moon;
import sofuni.exam.models.enums.Type;
import sofuni.exam.repository.DiscovererRepository;
import sofuni.exam.repository.MoonRepository;
import sofuni.exam.repository.PlanetRepository;
import sofuni.exam.service.MoonService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import sofuni.exam.util.ValidationUtil;
import sofuni.exam.util.XmlParser;

@Service
public class MoonServiceImpl implements MoonService {

    private static final String FILE_PATH = "src/main/resources/files/xml/moons.xml";
    private final MoonRepository moonRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final PlanetRepository planetRepository;
    private final DiscovererRepository discovererRepository;

    @Autowired
    public MoonServiceImpl(MoonRepository moonRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, PlanetRepository planetRepository, DiscovererRepository discovererRepository) {
        this.moonRepository = moonRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.planetRepository = planetRepository;
        this.discovererRepository = discovererRepository;
    }


    @Override
    public boolean areImported() {
        return this.moonRepository.count() > 0;
    }

    @Override
    public String readMoonsFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importMoons() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        MoonsDTO moonsDTO = this.xmlParser.fromFile(FILE_PATH, MoonsDTO.class);
        for (MoonDTO moonDTO : moonsDTO.getMoonDTOs()) {
            if (!validationUtil.isValid(moonDTO) ||
                    moonRepository.findByName(moonDTO.getName()).isPresent()) {
                sb.append("Invalid moon").append(System.lineSeparator());
                continue;
            }
            Moon moon = this.modelMapper.map(moonDTO, Moon.class);
            moon.setPlanet(planetRepository.findById((long) moonDTO.getPlanet()).get());
            moon.setDiscoverer(discovererRepository.findById((long) moonDTO.getDiscoverer()).get());
            moonRepository.saveAndFlush(moon);
            sb.append(String.format("Successfully imported moon %s", moonDTO.getName())).append(System.lineSeparator());
        }

        return sb.toString();
    }

    @Override
    public String exportMoons() {
        StringBuilder sb = new StringBuilder();
         // List<Moon> moons = this.moonRepository.findAllByRadiusGreaterThanEqualAndRadiusLessThanEqualAndPlanetTypeOrderByNameAsc(700, 2000, Type.valueOf("GAS_GIANT"));
        //List<Moon> moons = this.moonRepository.findAllByOrderByNameAsc();
    //  List<Moon> moons = this.moonRepository.findAllByPlanetTypeOrderByNameAsc(Type.valueOf("GAS_GIANT"));
      //  List<Moon> moons = this.moonRepository.findAllByRadiusBetweenOrderByNameAsc(700, 2000);
List<Moon> moons = this.moonRepository.findAllByPlanetTypeAndRadiusBetweenOrderByNameAsc(Type.valueOf("GAS_GIANT"), 700, 2000);
        for (Moon moon : moons) {
        //    if (moon.getPlanet().getType().equals(Type.valueOf("GAS_GIANT"))) {
            //&&
                   // moon.getRadius() >= 700 && moon.getRadius() <= 2000) {
                sb.append(String.format("***Moon %s is a natural satellite of %s and has a radius of %.2f km.\n" +
                                        "****Discovered by %s %s\n",
                                moon.getName(), moon.getPlanet().getName(), moon.getRadius(),
                                moon.getDiscoverer().getFirstName(), moon.getDiscoverer().getLastName()))
                        .append(System.lineSeparator());
            }
        return sb.toString();

        }


    }

