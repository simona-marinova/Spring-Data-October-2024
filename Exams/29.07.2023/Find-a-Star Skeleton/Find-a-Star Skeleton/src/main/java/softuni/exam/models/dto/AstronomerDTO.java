package softuni.exam.models.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="astronomer")
@XmlAccessorType(XmlAccessType.FIELD)
public class AstronomerDTO {

    //   <astronomer>
    //        <average_observation_hours>176858.79</average_observation_hours>
    //        <birthday>1989-01-01</birthday>
    //        <first_name>Drake</first_name>
    //        <last_name>Hawthorne</last_name>
    //        <salary>207615.71</salary>
    //        <observing_star_id>50</observing_star_id>
    //    </astronomer>

    @XmlElement(name="average_observation_hours")
    @Min(value =500)
    private double averageObservationHours;

    //yyyy-MM-dd
    @XmlElement()
    private String birthday;

    @XmlElement(name="first_name")
    @Length(min=2, max = 30)
    private String firstName;

    @XmlElement(name="last_name")
    @Length(min=2, max = 30)
    private String lastName;

    @XmlElement
    @Min(value = 15000)
    private double salary;

    @XmlElement(name="observing_star_id")
    private int observingStarId;

    public AstronomerDTO() {
    }

    public double getAverageObservationHours() {
        return averageObservationHours;
    }

    public void setAverageObservationHours(double averageObservationHours) {
        this.averageObservationHours = averageObservationHours;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getObservingStarId() {
        return observingStarId;
    }

    public void setObservingStarId(int observingStarId) {
        this.observingStarId = observingStarId;
    }
}
