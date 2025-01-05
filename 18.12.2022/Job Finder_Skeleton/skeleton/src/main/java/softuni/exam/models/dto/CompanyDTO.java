package softuni.exam.models.dto;


import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.AccessType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name ="company")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyDTO {

    //<companies>
    //    <company>
    //        <companyName>Heaney-Vandervort</companyName>
    //        <dateEstablished>1981-10-06</dateEstablished>
    //        <website>karley.biz</website>
    //        <countryId>34</countryId>
    //    </company>


    @XmlElement(name="companyName")
    @Length(min=2, max=40)
    private String name;

    //"yyyy-MM-dd"
    @XmlElement
    private String dateEstablished;


    @XmlElement
    @Length(min=2, max=30)
    private String website;


    @XmlElement(name="countryId")
    private int country;

    public CompanyDTO() {
    }

    public CompanyDTO(String name, String dateEstablished, String website, int country) {
        this.name = name;
        this.dateEstablished = dateEstablished;
        this.website = website;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateEstablished() {
        return dateEstablished;
    }

    public void setDateEstablished(String dateEstablished) {
        this.dateEstablished = dateEstablished;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }


}
