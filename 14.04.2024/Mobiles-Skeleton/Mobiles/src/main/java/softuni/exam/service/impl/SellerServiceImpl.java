package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.SellerImportDTO;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SellerServiceImpl implements SellerService {
    private static final String FILE_PATH = "C:\\Users\\stamb\\Desktop\\exams\\14.04.2024\\Mobiles-Skeleton\\Mobiles\\src\\main\\resources\\files\\json\\sellers.json";
    private final SellerRepository sellerRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;


    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.sellerRepository = sellerRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.sellerRepository.count()>0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importSellers() throws IOException {
        StringBuilder sb = new StringBuilder();
        SellerImportDTO[] sellerImportDTOS = this.gson.fromJson(readSellersFromFile(), SellerImportDTO[].class);
        for (SellerImportDTO sellerImportDTO : sellerImportDTOS) {
            if (this.sellerRepository.findByLastName(sellerImportDTO.getLastName()).isPresent()
                    || !this.validationUtil.isValid(sellerImportDTO)) {
                sb.append("Invalid seller").append(System.lineSeparator());
                continue;
            }
            this.sellerRepository.saveAndFlush(this.modelMapper.map(sellerImportDTO, Seller.class));
            sb.append(String.format("Successfully imported seller %s %s",
                    sellerImportDTO.getFirstName(), sellerImportDTO.getLastName())).append(System.lineSeparator());

        }

        return sb.toString();
    }

}
