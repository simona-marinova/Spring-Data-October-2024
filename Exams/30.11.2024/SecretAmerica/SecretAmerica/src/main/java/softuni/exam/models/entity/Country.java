package softuni.exam.models.entity;


import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="countries")
public class Country extends BaseEntity{

    @Column()
    private double area;

    @Column(nullable = false,unique = false)
    private String name;

//    @ManyToOne()
//    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false)
//    private Country country;

    @OneToMany(mappedBy="country", fetch = FetchType.EAGER )
    private Set<Attraction> attractions;

    public Set<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(Set<Attraction> attractions) {
        this.attractions = attractions;
    }

    public Country() {
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
