package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.DeviceImportDTO;
import softuni.exam.models.dto.DevicesImportDTO;
import softuni.exam.models.entity.Device;
import softuni.exam.models.entity.DeviceType;
import softuni.exam.repository.DeviceRepository;
import softuni.exam.repository.SaleRepository;
import softuni.exam.service.DeviceService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {

    private static final String FILE_PATH = "C:\\Users\\stamb\\Desktop\\exams\\14.04.2024\\Mobiles-Skeleton\\Mobiles\\src\\main\\resources\\files\\xml\\devices.xml";
    private final DeviceRepository deviceRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final SaleRepository saleRepository;


    @Autowired
    public DeviceServiceImpl(DeviceRepository deviceRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, SaleRepository saleRepository) {
        this.deviceRepository = deviceRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.saleRepository = saleRepository;
    }

    @Override
    public boolean areImported() {
        return this.deviceRepository.count() > 0;
    }

    @Override
    public String readDevicesFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importDevices() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        DevicesImportDTO devicesImportDTO = this.xmlParser.fromFile(FILE_PATH, DevicesImportDTO.class);
        for (DeviceImportDTO deviceImportDTO : devicesImportDTO.getDeviceImportDTOList()) {
            if (this.deviceRepository.findByModelAndBrand(deviceImportDTO.getModel(),
                    deviceImportDTO.getBrand()).isPresent() ||
            !validationUtil.isValid(deviceImportDTO) ||
                    !saleRepository.existsById((long) deviceImportDTO.getSaleId())) {
                sb.append("Invalid device").append(System.lineSeparator());
                continue;
            }
            Device device = this.modelMapper.map(deviceImportDTO, Device.class);
            device.setSale(this.saleRepository.findById((long) deviceImportDTO.getSaleId()).get());
            //device.setBrand(deviceImportDTO.getBrand().toLowerCase());
            this.deviceRepository.save(device);
            sb.append(String.format("Successfully imported device of type %s with brand %s", deviceImportDTO.getDeviceType(), deviceImportDTO.getBrand())).append(System.lineSeparator());

        }
        return sb.toString();
    }

    @Override
    public String exportDevices() {
      //  return this.deviceRepository.findAllByDeviceTypeAndPriceLessThanAndStorageGreaterThanEqualOrderByBrand(DeviceType.SMART_PHONE,
                  //      1000.00, 128)
              //  .stream()
              //  .map(device -> String.format("Device brand: %s\n" +
                              //  "   *Model: %s\n" +
                             //   "   **Storage: %d\n" +
                             //   "   ***Price: %.2f\n", device.getBrand(),
                       // device.getModel(), device.getStorage(), device.getPrice()))
               // .collect(Collectors.joining());
        StringBuilder sb = new StringBuilder();
        List<Device> devices = this.deviceRepository.findAllByDeviceTypeAndPriceLessThanAndStorageGreaterThanEqualOrderByBrand(DeviceType.SMART_PHONE, 1000, 128);
        for (Device device : devices) {
            sb.append(String.format("Device brand: %s\n" +
                             "   *Model: %s\n" +
                               "   **Storage: %d\n" +
                               "   ***Price: %.2f", device.getBrand(),
                             device.getModel(), device.getStorage(), device.getPrice()))
                    .append(System.lineSeparator());
        }

        return sb.toString();


    }


}


