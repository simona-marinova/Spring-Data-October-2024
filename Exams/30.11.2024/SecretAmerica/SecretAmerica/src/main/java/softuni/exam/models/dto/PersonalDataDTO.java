package softuni.exam.models.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

@XmlRootElement(name = "personal_data")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonalDataDTO {
    //<personal_data>
    //        <age>33</age>
    //        <gender>M</gender>
    //        <birth_date>1991-05-12</birth_date>
    //        <card_number>123456789</card_number>
    //    </personal_data>

    @XmlElement
    @Min(1)
    @Max(100)
    private int age;

    //M or F.
    @XmlElement
    private char gender;

    //The date must be in the past.
    @XmlElement(name = "birth_date")
    private String birthDate;


    @XmlElement(name = "card_number")
    @Length(min = 9, max = 9)
    private String cardNumber;

    public PersonalDataDTO() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
