package sofuni.exam.models.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "moons")
public class Moon extends BaseEntity {

    @Column(nullable = false)
    private LocalDate discovered;

    //can be  null
    @Column(name = "distance_from_planet")
    private int distanceFromPlanet;


    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private double radius;

    //relation with planets - cannot be null
    @ManyToOne
    @JoinColumn(name = "planet_id", referencedColumnName = "id", nullable = false)
    private Planet planet;

    // relation with discoverers  - cannot be null
    @ManyToOne
    @JoinColumn(name = "discoverer_id", referencedColumnName = "id", nullable = false)
    private Discoverer discoverer;

    public Moon() {
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public Discoverer getDiscoverer() {
        return discoverer;
    }

    public void setDiscoverer(Discoverer discoverer) {
        this.discoverer = discoverer;
    }

    public LocalDate getDiscovered() {
        return discovered;
    }

    public void setDiscovered(LocalDate discovered) {
        this.discovered = discovered;
    }

    public int getDistanceFromPlanet() {
        return distanceFromPlanet;
    }

    public void setDistanceFromPlanet(int distanceFromPlanet) {
        this.distanceFromPlanet = distanceFromPlanet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
