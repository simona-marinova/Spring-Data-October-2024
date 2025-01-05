package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ForecastDTO;
import softuni.exam.models.dto.ForecastsDTO;
import softuni.exam.models.entity.DayOfWeek;
import softuni.exam.models.entity.Forecast;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.ForecastService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Time;
import java.util.List;

@Service
public class ForecastServiceImpl implements ForecastService {

    private static final String FILE_PATH = "C:\\Users\\stamb\\Desktop\\exams\\17.04.2022\\SoftWeather Forecast_Skeleton\\skeleton\\src\\main\\resources\\files\\xml\\forecasts.xml";
    private final ForecastRepository forecastRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final CityRepository cityRepository;

    @Autowired
    public ForecastServiceImpl(ForecastRepository forecastRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, CityRepository cityRepository) {
        this.forecastRepository = forecastRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.cityRepository = cityRepository;
    }


    @Override
    public boolean areImported() {
        return this.forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        ForecastsDTO forecastsDTO = this.xmlParser.fromFile(FILE_PATH, ForecastsDTO.class);
        for (ForecastDTO forecastDTO : forecastsDTO.getForecasts()) {
            if (!validationUtil.isValid(forecastDTO) ||
                    forecastDTO.getDayOfWeek().equals("NULL") ||
                    (this.forecastRepository.findByDayOfWeekAndCityId(DayOfWeek.valueOf(forecastDTO.getDayOfWeek()),(long) forecastDTO.getCity()).isPresent())) {
                sb.append("Invalid forecast").append(System.lineSeparator());
                continue;
            }
            Forecast forecast = this.modelMapper.map(forecastDTO, Forecast.class);
            forecast.setCity(cityRepository.findById((long) forecastDTO.getCity()).get());
            forecast.setDayOfWeek(DayOfWeek.valueOf(forecastDTO.getDayOfWeek()));
            forecast.setSunrise(Time.valueOf(forecastDTO.getSunrise()));
            forecast.setSunset(Time.valueOf(forecastDTO.getSunset()));
            forecastRepository.saveAndFlush(forecast);
            sb.append(String.format("Successfully import forecast %s - %.2f",
                    forecastDTO.getDayOfWeek(), forecastDTO.getMaxTemperature())).append(System.lineSeparator());
        }

        return sb.toString();
    }

    @Override
    public String exportForecasts() {
        StringBuilder sb = new StringBuilder();
        List<Forecast> forecasts = this.forecastRepository.findAllByDayOfWeekAndCityPopulationLessThanOrderByMaxTemperatureDescIdAsc(DayOfWeek.SUNDAY, 150000);
        for (Forecast forecast : forecasts) {
            sb.append(String.format("City: %s:\n" +
                                    "     -min temperature: %.2f\n" +
                                    "     --max temperature: %.2f\n" +
                                    "     ---sunrise: %s\n" +
                                    "     ----sunset: %s", forecast.getCity().getCityName(),
                            forecast.getMinTemperature(),forecast.getMaxTemperature(),
                    forecast.getSunrise().toString(), forecast.getSunset().toString()))
                    .append(System.lineSeparator());
        }


        return sb.toString();

    }

}

