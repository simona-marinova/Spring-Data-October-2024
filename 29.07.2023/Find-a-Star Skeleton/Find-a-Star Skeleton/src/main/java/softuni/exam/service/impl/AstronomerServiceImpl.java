package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AstronomerDTO;
import softuni.exam.models.dto.AstronomersDTO;
import softuni.exam.models.entity.Astronomer;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.AstronomerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class AstronomerServiceImpl implements AstronomerService {

    private static final String FILE_PATH = "src/main/resources/files/xml/astronomers.xml";
    private final AstronomerRepository astronomerRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final StarRepository starRepository;


    @Autowired
    public AstronomerServiceImpl(AstronomerRepository astronomerRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, StarRepository starRepository) {
        this.astronomerRepository = astronomerRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.starRepository = starRepository;
    }

    @Override
    public boolean areImported() {
        return this.astronomerRepository.count()>0;
    }

    @Override
    public String readAstronomersFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importAstronomers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        AstronomersDTO astronomersDTO = this.xmlParser.fromFile(FILE_PATH, AstronomersDTO.class);
        for (AstronomerDTO astronomerDTO : astronomersDTO.getAstronomerDTOs()) {
            if(!validationUtil.isValid(astronomerDTO) ||
                    this.astronomerRepository.findByFirstNameAndLastName(astronomerDTO.getFirstName(),
                            astronomerDTO.getLastName()).isPresent() ||
            this.starRepository.findById((long) astronomerDTO.getObservingStarId()).isEmpty()) {
                sb.append("Invalid astronomer").append(System.lineSeparator());
                continue;
            }
            Astronomer astronomer = this.modelMapper.map(astronomerDTO, Astronomer.class);
            astronomer.setStar(this.starRepository.findById((long)astronomerDTO.getObservingStarId()).get());
this.astronomerRepository.saveAndFlush(astronomer);
sb.append(String.format("Successfully imported astronomer %s %s - %.2f",
        astronomer.getFirstName(), astronomer.getLastName(), astronomer.getAverageObservationHours())).append(System.lineSeparator());

        }
        return sb.toString();
    }
}
