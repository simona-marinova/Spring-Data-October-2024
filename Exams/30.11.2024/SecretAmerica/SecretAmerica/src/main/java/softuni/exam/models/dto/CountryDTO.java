package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

public class CountryDTO {
    //{"name": "Afghanistan", "area": 652230},

    @Expose
    @Length(min=3, max=40)
    private String name;

    @Expose
    @Min(value=0)
    private double area;

    public CountryDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
