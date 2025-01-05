package softuni.exam.models.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name="personal_datas")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonalDatasDTO {

    @XmlElement(name="personal_data")
    private List<PersonalDataDTO> personalDataDTOList;

    public PersonalDatasDTO() {
    }

    public PersonalDatasDTO(List<PersonalDataDTO> personalDataDTOList) {
        this.personalDataDTOList = personalDataDTOList;
    }

    public List<PersonalDataDTO> getPersonalDataDTOList() {
        return personalDataDTOList;
    }

    public void setPersonalDataDTOList(List<PersonalDataDTO> personalDataDTOList) {
        this.personalDataDTOList = personalDataDTOList;
    }
}
