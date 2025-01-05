package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;

public class StarDTO {

    //"description": "Glowing sphere of celestial gas",
    //    "lightYears": 25.34,
    //    "name": "X",
    //    "starType": "WHITE_DWARF",
    //    "constellation": 18

    @Expose
    @Length(min=6)
    private String description;

    @Expose
    @Min(0)
    private double lightYears;

    @Expose
    @Length(min=2, max=30)
    private String name;

    @Expose
    private String starType;

    @Expose
    private int constellation;

    public StarDTO() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLightYears() {
        return lightYears;
    }

    public void setLightYears(double lightYears) {
        this.lightYears = lightYears;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStarType() {
        return starType;
    }

    public void setStarType(String starType) {
        this.starType = starType;
    }

    public int getConstellation() {
        return constellation;
    }

    public void setConstellation(int constellation) {
        this.constellation = constellation;
    }
}
