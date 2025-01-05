package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import jdk.jfr.Name;
import org.hibernate.validator.constraints.Length;

public class CountryDTO {
    //{
    //    "name": "Albania",
    //    "countryCode": "A",
    //    "currency": "Lek"
    //  },

    @Expose
    @Length(min = 2, max = 30)
    private String name;

    @Expose
    @Length(min = 2, max = 19)
    private String countryCode;

    @Expose
    @Length(min = 2, max = 19)
    private String currency;

    public CountryDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


}
