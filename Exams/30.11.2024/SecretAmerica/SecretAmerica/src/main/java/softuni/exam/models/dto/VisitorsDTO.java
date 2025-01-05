package softuni.exam.models.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "visitors")
@XmlAccessorType(XmlAccessType.FIELD)
public class VisitorsDTO {

    @XmlElement(name="visitor")
    private List<VisitorDTO> visitorDTOList;

    public VisitorsDTO() {
    }

    public List<VisitorDTO> getVisitorDTOList() {
        return visitorDTOList;
    }

    public void setVisitorDTOList(List<VisitorDTO> visitorDTOList) {
        this.visitorDTOList = visitorDTOList;
    }
}
