package sofuni.exam.models.dto;

import jakarta.validation.constraints.Min;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Length;

@XmlRootElement(name="moon")
@XmlAccessorType(XmlAccessType.FIELD)
public class MoonDTO {
    // <name>Moon</name>
    //        <discovered>1609-11-30</discovered>
    //        <distance_from_planet>384400</distance_from_planet>
    //        <radius>1737.4</radius>
    //        <discoverer_id>1</discoverer_id>
    //        <planet_id>3</planet_id>

    @XmlElement
   @Length(min=2, max=10)
    private String name;

    @XmlElement
    private String discovered;

    @XmlElement(name="distance_from_planet")
    @Min(0)
    private int distanceFromPlanet;

    @XmlElement
    @Min(0)
    private double radius;

    @XmlElement(name="discoverer_id")
    private int discoverer;

    @XmlElement(name="planet_id")
    private int planet;

    public MoonDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscovered() {
        return discovered;
    }

    public void setDiscovered(String discovered) {
        this.discovered = discovered;
    }

    public int getDistanceFromPlanet() {
        return distanceFromPlanet;
    }

    public void setDistanceFromPlanet(int distanceFromPlanet) {
        this.distanceFromPlanet = distanceFromPlanet;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public int getDiscoverer() {
        return discoverer;
    }

    public void setDiscoverer(int discoverer) {
        this.discoverer = discoverer;
    }

    public int getPlanet() {
        return planet;
    }

    public void setPlanet(int planet) {
        this.planet = planet;
    }
}
