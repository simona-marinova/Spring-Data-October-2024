package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PersonDTO;
import softuni.exam.models.entity.Person;
import softuni.exam.models.entity.StatusType;
import softuni.exam.repository.CountryRepository;
import softuni.exam.repository.PersonRepository;
import softuni.exam.service.PersonService;
import softuni.exam.util.ValidationUtil;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PersonServiceImpl implements PersonService {
    private static final String FILE_PATH = "C:\\Users\\stamb\\Desktop\\exams\\18.12.2022\\Job Finder_Skeleton\\skeleton\\src\\main\\resources\\files\\json\\people.json";
private final PersonRepository personRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final CountryRepository countryRepository;

    public PersonServiceImpl(PersonRepository personRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, CountryRepository countryRepository) {
        this.personRepository = personRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.countryRepository = countryRepository;
    }

    @Override
    public boolean areImported() {
        return this.personRepository.count()>0;
    }

    @Override
    public String readPeopleFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importPeople() throws IOException, JAXBException {
       StringBuilder sb = new StringBuilder();
//email (must contains ‘@’ and ‘.’ – dot).
//•	If a person with the same first name, email OR
// phone number already exists in the DB return "Invalid person".
        PersonDTO[] personDTOS = this.gson.fromJson(readPeopleFromFile(), PersonDTO[].class);
        for (PersonDTO personDTO : personDTOS) {
            if(!validationUtil.isValid(personDTO) ||
                    !(personDTO.getEmail().contains("@") && personDTO.getEmail().contains(".")) ||
            personRepository.findByFirstName(personDTO.getFirstName()).isPresent() ||
            personRepository.findByEmail(personDTO.getEmail()).isPresent() ||
            personRepository.findByPhone(personDTO.getPhone()).isPresent()) {
                sb.append("Invalid person").append(System.lineSeparator());
                continue;
            }
            Person person = this.modelMapper.map(personDTO, Person.class);
person.setStatusType(StatusType.valueOf(personDTO.getStatusType().toUpperCase()));
person.setCountry(countryRepository.findById((long) personDTO.getCountry()).get());
personRepository.saveAndFlush(person);
            sb.append(String.format("Successfully imported person %s %s", personDTO.getFirstName(), personDTO.getLastName())).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
