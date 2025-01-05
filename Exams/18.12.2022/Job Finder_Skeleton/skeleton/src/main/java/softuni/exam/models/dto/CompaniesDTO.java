package softuni.exam.models.dto;

import org.springframework.data.annotation.AccessType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name ="companies")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompaniesDTO {

    @XmlElement(name="company")
    private List<CompanyDTO> companies;

    public CompaniesDTO() {
    }

    public CompaniesDTO(List<CompanyDTO> companies) {
        this.companies = companies;
    }

    public List<CompanyDTO> getCompanies() {
        return companies;
    }

    public void setCompanies(List<CompanyDTO> companies) {
        this.companies = companies;
    }
}
