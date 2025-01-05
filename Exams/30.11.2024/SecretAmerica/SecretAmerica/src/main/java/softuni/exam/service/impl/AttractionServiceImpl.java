package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AttractionDTO;
import softuni.exam.models.dto.CountryDTO;
import softuni.exam.models.entity.Attraction;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.AttractionRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.AttractionService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//ToDo - Implement all the methods
@Service

public class AttractionServiceImpl implements AttractionService {

    private static final String FILE_PATH = "C:\\Users\\stamb\\Desktop\\exam now\\SecretAmerica\\SecretAmerica\\src\\main\\resources\\files\\json\\attractions.json";
    private final AttractionRepository attractionRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final CountryRepository countryRepository;

    @Autowired
    public AttractionServiceImpl(AttractionRepository attractionRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, CountryRepository countryRepository) {
        this.attractionRepository = attractionRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.countryRepository = countryRepository;
    }

    @Override
    public boolean areImported() {
        return this.attractionRepository.count() > 0;
    }

    @Override
    public String readAttractionsFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importAttractions() throws IOException {
        StringBuilder sb = new StringBuilder();
        AttractionDTO[] attractionDTOs = this.gson.fromJson(readAttractionsFileContent(), AttractionDTO[].class);
        for (AttractionDTO attractionDTO : attractionDTOs) {
            if (this.attractionRepository.findByName(attractionDTO.getName()).isPresent()
                    || !this.validationUtil.isValid(attractionDTO)) {
                sb.append("Invalid attraction").append(System.lineSeparator());
                continue;
            }
            Attraction attraction = this.modelMapper.map(attractionDTO, Attraction.class);
            attraction.setCountry(countryRepository.findById((long) attractionDTO.getCountry()).get());
            this.attractionRepository.saveAndFlush(attraction);
            sb.append(String.format("Successfully imported attraction %s",
                    attractionDTO.getName())).append(System.lineSeparator());

        }

        return sb.toString();
    }


    @Override
   public String exportAttractions() {
        //  "type": "archaeological site",
        //   "type": "historical site",
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();
        list.add("archaeological site");
        list.add("historical site");
        List<Attraction> attractions = this.attractionRepository
                .findAllByTypeInAndElevationGreaterThanEqualOrderByNameAscCountryNameAsc(list, 300);
        for (Attraction attraction : attractions) {
            sb.append(String.format("Attraction with ID%d:\n" +
                                    "***%s - %s at an altitude of %dm. somewhere in %s.",
                            attraction.getId(), attraction.getName(), attraction.getDescription(),
                            attraction.getElevation(), attraction.getCountry().getName()))
                    .append(System.lineSeparator());
        }


        return sb.toString();

    }


}
