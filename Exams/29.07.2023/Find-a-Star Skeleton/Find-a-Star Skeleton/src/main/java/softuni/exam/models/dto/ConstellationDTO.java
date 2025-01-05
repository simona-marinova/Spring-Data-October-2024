package softuni.exam.models.dto;


import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

public class ConstellationDTO {
    //"name": "Andromeda",
    //    "description": "A princess chained to a rock, saved by Perseus."

    @Expose
    @Length(min=3, max=20)
    private String name;

    @Expose
    @Length(min=5)
    private String description;

    public ConstellationDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
