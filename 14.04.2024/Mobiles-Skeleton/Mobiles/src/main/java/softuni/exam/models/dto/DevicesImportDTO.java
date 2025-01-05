package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "devices")
@XmlAccessorType(XmlAccessType.FIELD)
public class DevicesImportDTO {

    @XmlElement(name="device")
    private List<DeviceImportDTO> deviceImportDTOList;

    public DevicesImportDTO(List<DeviceImportDTO> deviceImportDTOList) {
        this.deviceImportDTOList = deviceImportDTOList;
    }

    public DevicesImportDTO() {
    }

    public List<DeviceImportDTO> getDeviceImportDTOList() {
        return deviceImportDTOList;
    }

    public void setDeviceImportDTOList(List<DeviceImportDTO> deviceImportDTOList) {
        this.deviceImportDTOList = deviceImportDTOList;
    }
}
