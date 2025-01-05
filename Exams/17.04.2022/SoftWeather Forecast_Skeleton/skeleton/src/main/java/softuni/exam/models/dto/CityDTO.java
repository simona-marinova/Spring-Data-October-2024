package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;

public class CityDTO {
    //    "cityName": "g",
    //    "description": "ut dolor morbi vel lectus in quam fringilla rhoncus mauris",
    //    "population": 325899,
    //    "country": 1

    @Expose
    @Length(min=2,max=60)
    private String cityName;


    @Expose
    @Length(min=2)
    private String description;

    @Expose
    @Min(500)
    private int population;

    @Expose
    private int country;

    public CityDTO() {
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }
}
