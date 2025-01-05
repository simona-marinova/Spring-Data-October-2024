package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

public class CountryDTO {

    //  "countryName": "Philippines",
    //    "currency": "Peso"

    @Expose
    @Length(min=2, max =60)
    private String countryName;

    @Expose
    @Length(min=2, max=20)
    private String currency;

    public CountryDTO() {
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
