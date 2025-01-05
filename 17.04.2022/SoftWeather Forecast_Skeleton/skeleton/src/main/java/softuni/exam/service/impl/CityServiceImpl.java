package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CityDTO;
import softuni.exam.models.entity.City;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CityService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CityServiceImpl implements CityService {

    private static final String FILE_PATH = "C:\\Users\\stamb\\Desktop\\exams\\17.04.2022\\SoftWeather Forecast_Skeleton\\skeleton\\src\\main\\resources\\files\\json\\cities.json";
    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final CountryRepository countryRepository;


    @Autowired
    public CityServiceImpl(CityRepository cityRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.countryRepository = countryRepository;
    }

    @Override
    public boolean areImported() {
        return this.cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importCities() throws IOException {
        StringBuilder sb = new StringBuilder();
        CityDTO[] cityDTOS = this.gson.fromJson(readCitiesFileContent(), CityDTO[].class);
        for (CityDTO cityDTO : cityDTOS) {
            if (!this.validationUtil.isValid(cityDTO) ||
                    this.cityRepository.findByCityName(cityDTO.getCityName()).isPresent()) {
                sb.append("Invalid city").append(System.lineSeparator());
                continue;
            }
            City city = this.modelMapper.map(cityDTO, City.class);
            city.setCountry(countryRepository.findById((long) cityDTO.getCountry()).get());
            cityRepository.saveAndFlush(city);
sb.append(String.format("Successfully imported city %s - %d", cityDTO.getCityName(), cityDTO.getPopulation())).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
