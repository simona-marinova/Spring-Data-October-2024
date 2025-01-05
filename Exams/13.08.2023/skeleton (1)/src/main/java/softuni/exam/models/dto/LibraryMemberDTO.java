package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

public class LibraryMemberDTO {
    //    "address": "123 Main St",
    //    "firstName": "John",
    //    "lastName": "Doe",
    //    "phoneNumber": "555-1111"

    @Expose
    private Long id;

    @Expose
    @Length(min=2, max=40)
    private String address;

    @Expose
    @Length(min=2, max=30)
    private String firstName;

    @Expose
    @Length(min=2, max=30)
    private String lastName;

    @Expose
    @Length(min=2, max=20)
    private String phoneNumber;

    public LibraryMemberDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
