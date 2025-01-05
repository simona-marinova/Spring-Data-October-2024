package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.StarDTO;
import softuni.exam.models.entity.Star;
import softuni.exam.models.entity.StarType;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.StarService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class StarServiceImpl implements StarService {

    private static final String FILE_PATH = "C:\\Users\\stamb\\Desktop\\exams\\29.07.2023\\Find-a-Star Skeleton\\Find-a-Star Skeleton\\src\\main\\resources\\files\\json\\stars.json";
    private final StarRepository starRepository;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ConstellationRepository constellationRepository;

    @Autowired
    public StarServiceImpl(StarRepository starRepository, ValidationUtil validationUtil, Gson gson, ModelMapper modelMapper, ConstellationRepository constellationRepository) {
        this.starRepository = starRepository;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.constellationRepository = constellationRepository;
    }

    @Override
    public boolean areImported() {
        return this.starRepository.count()>0;
    }

    @Override
    public String readStarsFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importStars() throws IOException {
      StringBuilder sb = new StringBuilder();
        StarDTO[] starDTOS = this.gson.fromJson(readStarsFileContent(), StarDTO[].class);
        for (StarDTO starDTO : starDTOS) {
            if(!validationUtil.isValid(starDTO) || this.starRepository.findByName(starDTO.getName()).isPresent()) {
    sb.append("Invalid star").append(System.lineSeparator());
    continue;
            }

            Star star = this.modelMapper.map(starDTO, Star.class);
star.setConstellation(this.constellationRepository.findById((long)starDTO.getConstellation()).get());
this.starRepository.saveAndFlush(star);
sb.append(String.format("Successfully imported star %s - %.2f light years", star.getName(),
        star.getLightYears())).append(System.lineSeparator());
        }

        return sb.toString();
    }

    @Override
    public String exportStars() {
        StringBuilder sb = new StringBuilder();
        List<Star> stars = this.starRepository.findAllByStarTypeAndObserversEmptyOrderByLightYearsAsc(StarType.RED_GIANT);
        for (Star star : stars) {
            sb.append(String.format("Star: %s\n" +
                                    "   *Distance: %.2f light years\n" +
                                    "   **Description: %s\n" +
                                    "   ***Constellation: %s", star.getName(),
                            star.getLightYears(), star.getDescription(),
                            star.getConstellation().getName()))
                    .append(System.lineSeparator());
        }


        return sb.toString();

    }


}

