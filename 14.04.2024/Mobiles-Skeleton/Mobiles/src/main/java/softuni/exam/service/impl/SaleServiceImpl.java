package softuni.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.SaleImportDTO;
import softuni.exam.models.entity.Sale;
import softuni.exam.repository.SaleRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SaleService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class SaleServiceImpl implements SaleService {
    private static final String FILE_PATH = "C:\\Users\\stamb\\Desktop\\exams\\14.04.2024\\Mobiles-Skeleton\\Mobiles\\src\\main\\resources\\files\\json\\sales.json";
    private final SaleRepository saleRepository;
    private Gson gson;
    private ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final SellerRepository sellerRepository;


    public SaleServiceImpl(SaleRepository saleRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, SellerRepository sellerRepository) {
        this.saleRepository = saleRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public boolean areImported() {
        return this.saleRepository.count() > 0;
    }

    @Override
    public String readSalesFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importSales() throws IOException {
        StringBuilder sb = new StringBuilder();
        SaleImportDTO[] saleImportDTOS = this.gson.fromJson(readSalesFileContent(), SaleImportDTO[].class);
        for (SaleImportDTO saleImportDTO : saleImportDTOS) {
            if (this.saleRepository.findByNumber(saleImportDTO.getNumber()).isPresent() ||
                    saleImportDTO.getNumber().length() != 7 ||
                    !this.validationUtil.isValid(saleImportDTO)) {
                sb.append("Invalid sale").append(System.lineSeparator());
                continue;
            }
            Sale sale = this.modelMapper.map(saleImportDTO, Sale.class);
            sale.setSeller(sellerRepository.findById((long) saleImportDTO.getSeller()).get());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(saleImportDTO.getSaleDate(), formatter);
            Instant instant = localDateTime.atZone(ZoneId.of("UTC")).toInstant();
            sale.setSaleDate(instant);
            this.saleRepository.save(sale);
            sb.append(String.format("Successfully imported sale with number %s",
                    sale.getNumber())).append(System.lineSeparator());
        }
        return sb.toString();

    }
}
