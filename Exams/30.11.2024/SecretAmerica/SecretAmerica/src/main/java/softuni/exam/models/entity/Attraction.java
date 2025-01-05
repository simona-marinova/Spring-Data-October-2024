package softuni.exam.models.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "attractions")
public class Attraction extends BaseEntity {


    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int elevation;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String type;

    @ManyToOne()
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false)
    private Country country;


    @OneToMany(mappedBy = "attraction", fetch = FetchType.EAGER)
    private Set<Visitor> visitors;


    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(Set<Visitor> visitors) {
        this.visitors = visitors;
    }

    public Attraction() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
