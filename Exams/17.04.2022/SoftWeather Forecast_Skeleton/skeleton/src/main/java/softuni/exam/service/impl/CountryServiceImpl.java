package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountryDTO;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CountryServiceImpl implements CountryService {

    private static final String FILE_PATH = "C:\\Users\\stamb\\Desktop\\exams\\17.04.2022\\SoftWeather Forecast_Skeleton\\skeleton\\src\\main\\resources\\files\\json\\countries.json";
    private final CountryRepository countryRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return this.countryRepository.count()>0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importCountries() throws IOException {
       StringBuilder sb = new StringBuilder();
        CountryDTO[] countryDTOS = this.gson.fromJson(readCountriesFromFile(), CountryDTO[].class);
        for (CountryDTO countryDTO : countryDTOS) {
            if(!validationUtil.isValid(countryDTO) ||
            countryRepository.findByCountryName(countryDTO.getCountryName()).isPresent()) {
               sb.append("Invalid country").append(System.lineSeparator());
               continue;
            }
            Country country = this.modelMapper.map(countryDTO, Country.class);
            countryRepository.saveAndFlush(country);
            sb.append(String.format("Successfully imported country %s - %s", country.getCountryName(), country.getCurrency())).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
