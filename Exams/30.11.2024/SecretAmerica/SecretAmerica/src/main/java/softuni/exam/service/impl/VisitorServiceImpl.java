package softuni.exam.service.impl;

import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PersonalDataDTO;
import softuni.exam.models.dto.PersonalDatasDTO;
import softuni.exam.models.dto.VisitorDTO;
import softuni.exam.models.dto.VisitorsDTO;
import softuni.exam.models.entity.PersonalData;
import softuni.exam.models.entity.Visitor;
import softuni.exam.repository.AttractionRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.repository.PersonalDataRepository;
import softuni.exam.repository.VisitorRepository;
import softuni.exam.service.VisitorService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

//ToDo - Implement all the methods

@Service
public class VisitorServiceImpl implements VisitorService {
    private static final String FILE_PATH = "C:\\Users\\stamb\\Desktop\\exam now\\SecretAmerica\\SecretAmerica\\src\\main\\resources\\files\\xml\\visitors.xml";
    private final VisitorRepository visitorRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final PersonalDataRepository personalDataRepository;
    private final AttractionRepository attractionRepository;
    private final CountryRepository countryRepository;


    @Autowired
    public VisitorServiceImpl(VisitorRepository visitorRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, PersonalDataRepository personalDataRepository, AttractionRepository attractionRepository, CountryRepository countryRepository) {
        this.visitorRepository = visitorRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.personalDataRepository = personalDataRepository;
        this.attractionRepository = attractionRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public boolean areImported() {
        return this.visitorRepository.count() > 0;
    }

    @Override
    public String readVisitorsFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importVisitors() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        VisitorsDTO visitorsDTO = this.xmlParser.fromFile(FILE_PATH, VisitorsDTO.class);
        for (VisitorDTO visitorDTO : visitorsDTO.getVisitorDTOList()) {
            if (this.visitorRepository.findByFirstNameAndLastName(visitorDTO.getFirstName(),
                    visitorDTO.getLastName()).isPresent() ||
                    visitorRepository.findByPersonalDataId((long) visitorDTO.getPersonalDataId()).isPresent()
                    || !validationUtil.isValid(visitorDTO)) {
                sb.append("Invalid visitor").append(System.lineSeparator());
                continue;
            }

            Visitor visitor = this.modelMapper.map(visitorDTO, Visitor.class);

            if (attractionRepository.findById((long) visitorDTO.getAttraction()).isPresent()) {
                visitor.setAttraction(attractionRepository.findById((long) visitorDTO.getAttraction()).get());
            }
            if (countryRepository.findById((long) visitorDTO.getCountryId()).isPresent()) {
                visitor.setCountry(countryRepository.findById((long) visitorDTO.getCountryId()).get());
            }
            if (personalDataRepository.findById((long) visitorDTO.getPersonalDataId()).isPresent()) {
                visitor.setPersonalData(personalDataRepository.findById((long) visitorDTO.getPersonalDataId()).get());
            }
            this.visitorRepository.saveAndFlush(visitor);
            sb.append(String.format("Successfully imported visitor %s %s", visitorDTO.getFirstName(),
                    visitorDTO.getLastName())).append(System.lineSeparator());

        }
        return sb.toString();
    }

}