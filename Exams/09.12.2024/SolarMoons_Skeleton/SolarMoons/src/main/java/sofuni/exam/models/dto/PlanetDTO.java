package sofuni.exam.models.dto;


import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

public class PlanetDTO {
    // "name": "Mercury",
    //    "diameter": 4879,
    //    "distanceFromSun": 57910000,
    //    "orbitalPeriod": 87.97,
    //    "type": "TERRESTRIAL"

    @Expose
    @Length(min = 3, max = 20)
    private String name;

    @Expose
    @Min(0)
    private int diameter;

    @Expose
    @Min(0)
    private Long distanceFromSun;

    @Expose
    @Min(0)
    private double orbitalPeriod;

    @Expose
    private String type;

    public PlanetDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public Long getDistanceFromSun() {
        return distanceFromSun;
    }

    public void setDistanceFromSun(Long distanceFromSun) {
        this.distanceFromSun = distanceFromSun;
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(double orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
