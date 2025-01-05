package softuni.exam.models.dto;


import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "device")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceImportDTO {

//<device>
//        <brand>HTC</brand>
//        <device_type>SMART_PHONE</device_type>
//        <model>Ultra23+</model>
//        <price>999.00</price>
//        <storage>128</storage>
//        <sale_id>1</sale_id>
//    </device>

    @XmlElement
    @Length(min=2, max=20)
    private String brand;

    @XmlElement(name="device_type")
    @Enumerated(EnumType.STRING)
    private String deviceType;

    @XmlElement
    @Length(min=1, max=20)
    private String model;

    @XmlElement
   @Min(value=0)
    private double price;

    @XmlElement
    @Min(value=0)
    private int storage;

    @XmlElement(name="sale_id")
    private int saleId;

    public DeviceImportDTO() {
    }

    public DeviceImportDTO(String brand, String deviceType, String model, double price, int storage, int saleId) {
        this.brand = brand;
        this.deviceType = deviceType;
        this.model = model;
        this.price = price;
        this.storage = storage;
        this.saleId = saleId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }
}
