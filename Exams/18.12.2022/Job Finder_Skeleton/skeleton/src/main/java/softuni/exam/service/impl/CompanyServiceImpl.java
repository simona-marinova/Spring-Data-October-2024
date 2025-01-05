package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CompaniesDTO;
import softuni.exam.models.dto.CompanyDTO;
import softuni.exam.models.entity.Company;
import softuni.exam.repository.CompanyRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CompanyService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private static final String FILE_PATH = "C:\\Users\\stamb\\Desktop\\exams\\18.12.2022\\Job Finder_Skeleton\\skeleton\\src\\main\\resources\\files\\xml\\companies.xml";
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final CountryRepository countryRepository;


    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil, CountryRepository countryRepository) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.countryRepository = countryRepository;
    }

    @Override
    public boolean areImported() {
        return this.companyRepository.count() > 0;
    }

    @Override
    public String readCompaniesFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importCompanies() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        CompaniesDTO companiesDTO = this.xmlParser.fromFile(FILE_PATH, CompaniesDTO.class);
        List<CompanyDTO> companyDTOList = companiesDTO.getCompanies();
        for (CompanyDTO companyDTO : companiesDTO.getCompanies()) {
            if (!validationUtil.isValid(companyDTO) ||
                    companyRepository.findByName(companyDTO.getName()).isPresent()
            ) {
                sb.append("Invalid company").append(System.lineSeparator());
                continue;
            }
            Company company = this.modelMapper.map(companyDTO, Company.class);
            company.setCountry(this.countryRepository.findById((long) companyDTO.getCountry()).get());
            companyRepository.saveAndFlush(company);
            sb.append(String.format("Successfully imported company %s - %d", company.getName(),
                    companyDTO.getCountry())).append(System.lineSeparator());

        }
        return sb.toString();
    }
}