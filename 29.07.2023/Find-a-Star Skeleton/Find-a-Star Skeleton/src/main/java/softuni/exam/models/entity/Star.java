package softuni.exam.models.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="stars")
public class Star extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name="light_years", nullable = false)
    private double lightYears;

    @Column(nullable = false)
    @Lob
    private String description;

    @Column(name = "star_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private StarType starType;

    @OneToMany(mappedBy = "star", fetch = FetchType.EAGER)
    private List<Astronomer> observers;

    @ManyToOne
    @JoinColumn(name ="constellation_id", referencedColumnName = "id")
    private Constellation constellation;

    public Star() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLightYears() {
        return lightYears;
    }

    public void setLightYears(double lightYears) {
        this.lightYears = lightYears;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StarType getStarType() {
        return starType;
    }

    public void setStarType(StarType starType) {
        this.starType = starType;
    }

    public List<Astronomer> getObservers() {
        return observers;
    }

    public void setObservers(List<Astronomer> observers) {
        this.observers = observers;
    }

    public Constellation getConstellation() {
        return constellation;
    }

    public void setConstellation(Constellation constellation) {
        this.constellation = constellation;
    }
}
