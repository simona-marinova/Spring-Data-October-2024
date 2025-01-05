package sofuni.exam.service.Impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sofuni.exam.models.dto.DiscovererDTO;
import sofuni.exam.models.entity.Discoverer;
import sofuni.exam.repository.DiscovererRepository;
import sofuni.exam.service.DiscovererService;
import sofuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class DiscovererServiceImpl implements DiscovererService {
    private static final String FILE_PATH = "src/main/resources/files/json/discoverers.json";
    private final DiscovererRepository discovererRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;


    @Autowired
    public DiscovererServiceImpl(DiscovererRepository discovererRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.discovererRepository = discovererRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return this.discovererRepository.count()>0;
    }

    @Override
    public String readDiscovererFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importDiscoverers() throws IOException {
      StringBuilder sb = new StringBuilder();
        DiscovererDTO[] discovererDTOS =this.gson.fromJson(readDiscovererFileContent(), DiscovererDTO[].class);
        for (DiscovererDTO discovererDTO : discovererDTOS) {
            if(!validationUtil.isValid(discovererDTO) ||
                    discovererRepository.findByFirstNameAndLastName(discovererDTO.getFirstName(),
                            discovererDTO.getLastName()).isPresent()) {
                sb.append("Invalid discoverer").append(System.lineSeparator());
                continue;
            }
            Discoverer discoverer = this.modelMapper.map(discovererDTO, Discoverer.class);
discovererRepository.saveAndFlush(discoverer);
sb.append(String.format("Successfully imported discoverer %s %s", discovererDTO.getFirstName(),
        discovererDTO.getLastName())).append(System.lineSeparator());

        }
        return sb.toString();
    }
}
