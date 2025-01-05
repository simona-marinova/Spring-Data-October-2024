package sofuni.exam.models.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="discoverers")
public class Discoverer extends BaseEntity {


    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(columnDefinition="text", nullable = false)
    private String nationality;

    //can be nullable
    @Column
    private String occupation;

    // relation with moon cannot be null
//@ManyToOne
//@JoinColumn(name="discoverer_id", referencedColumnName = "id")
//private Discoverer discoverer;
    // тук дали не е SEt
    @OneToMany(mappedBy = "discoverer", fetch = FetchType.EAGER)
    private List<Moon> moons;

    public Discoverer() {
    }

    public List<Moon> getMoons() {
        return moons;
    }

    public void setMoons(List<Moon> moons) {
        this.moons = moons;
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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
