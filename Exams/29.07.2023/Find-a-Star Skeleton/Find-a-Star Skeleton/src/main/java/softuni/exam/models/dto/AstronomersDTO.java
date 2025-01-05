package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name ="astronomers")
@XmlAccessorType(XmlAccessType.FIELD)
public class AstronomersDTO {

    @XmlElement(name="astronomer")
    private List<AstronomerDTO> astronomerDTOs;

    public AstronomersDTO(List<AstronomerDTO> astronomerDTOs) {
        this.astronomerDTOs = astronomerDTOs;
    }

    public AstronomersDTO() {
    }

    public List<AstronomerDTO> getAstronomerDTOs() {
        return astronomerDTOs;
    }

    public void setAstronomerDTOs(List<AstronomerDTO> astronomerDTOs) {
        this.astronomerDTOs = astronomerDTOs;
    }
}
