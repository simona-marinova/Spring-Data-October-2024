package softuni.exam.models.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="job")
@XmlAccessorType(XmlAccessType.FIELD)
public class JobDTO {

    //<jobs>
    //    <job>
    //        <jobTitle>Assistant Professor</jobTitle>
    //        <hoursAWeek>21.1</hoursAWeek>
    //        <salary>5754.07</salary>
    //        <description>Cras pellentesque volutpat dui. Maecenas tristique, est et tempus semper, est quam pharetra magna,
    //            ac consequat metus sapien ut nunc.
    //        </description>
    //        <companyId>38</companyId>
    //    </job>

    //        <jobTitle>Senior Financial Analyst</jobTitle>
    //        <hoursAWeek>6.5</hoursAWeek>
    //        <salary>6541.34</salary>
    //        <description>Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem. Integer tincidunt ante
    //            vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.
    //        </description>
    //        <companyId>66</companyId>
    @XmlElement(name="jobTitle")
    @Length(min=2, max=40)
    private String title;

    @XmlElement
    @Min(value = 10)
    private double hoursAWeek;

    @XmlElement
    @Min(value = 300)
    private double salary;

    @XmlElement
    @Length(min=5)
    private String description;

    @XmlElement
    private int companyId;

    public JobDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getHoursAWeek() {
        return hoursAWeek;
    }

    public void setHoursAWeek(double hoursAWeek) {
        this.hoursAWeek = hoursAWeek;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
