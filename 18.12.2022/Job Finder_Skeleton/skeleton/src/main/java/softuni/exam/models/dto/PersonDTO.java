package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

public class PersonDTO {

    // "email": "lrann0@t-online.de",
    //    "firstName": "Lorna",
    //    "lastName": "Rann",
    //    "phone": "462-463-0432",
    //    "statusType": "freelancer",
    //    "country": "21"

    @Expose
    private String email;

    @Expose
    @Length(min =2, max = 30)
    private String firstName;

    @Expose
    @Length(min=2, max=30)
    private String lastName;

@Expose
@Length(min=2, max=13)
  private String phone;

@Expose
private String statusType;

@Expose
private int country;

    public PersonDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }
}
