package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Device;
import softuni.exam.models.entity.DeviceType;
import softuni.exam.models.entity.Sale;

import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {


    Optional<Device> findByModelAndBrand(String model, String brand);

    List<Device> findAllByDeviceTypeAndPriceLessThanAndStorageGreaterThanEqualIgnoreCaseOrderByBrandAsc(DeviceType type, double price, int storage);

    List<Device> findAllByDeviceType(DeviceType deviceType);
    //Export all devices of type SMART_PHONE with
    // a price less than 1000 and storage equal
    // to or more than 128 from the Database

    List<Device> findAllByDeviceTypeAndPriceLessThanAndStorageGreaterThanEqualOrderByBrand(DeviceType deviceType,
    double price, int storage);


}
