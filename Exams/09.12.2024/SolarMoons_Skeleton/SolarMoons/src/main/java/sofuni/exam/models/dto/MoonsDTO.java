package sofuni.exam.models.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name="moons")
@XmlAccessorType(XmlAccessType.FIELD)
public class MoonsDTO {

    @XmlElement(name="moon")
    private List<MoonDTO> moonDTOs;

    public MoonsDTO() {
    }

    public MoonsDTO(List<MoonDTO> moonDTOs) {
        this.moonDTOs = moonDTOs;
    }

    public List<MoonDTO> getMoonDTOs() {
        return moonDTOs;
    }

    public void setMoonDTOs(List<MoonDTO> moonDTOs) {
        this.moonDTOs = moonDTOs;
    }
}
