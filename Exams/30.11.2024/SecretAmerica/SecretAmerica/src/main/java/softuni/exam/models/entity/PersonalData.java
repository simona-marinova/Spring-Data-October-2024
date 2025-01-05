package softuni.exam.models.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="personal_datas")
public class PersonalData extends BaseEntity{

    @Column
    private int age;

    //"yyyy-MM-dd" format. The date must be in the past.
    @Column(name="birth_date")
    private LocalDate birthDate;

    @Column(name="card_number", nullable = false, unique = true)
    private String cardNumber;

//  @OneToOne
//    @JoinColumn(name = "personal_data_id", referencedColumnName = "id", nullable = false)
//    private PersonalData personalData;

@OneToOne(mappedBy = "personalData", fetch = FetchType.EAGER)
private Visitor visitor;

    //char 1 M or F
    @Column
    private char gender;

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public PersonalData() {
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}
