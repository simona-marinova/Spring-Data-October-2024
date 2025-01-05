package softuni.exam.models.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="countries")
public class Country extends BaseEntity{

    @Column(name="country_name", nullable = false, unique = true)
    private String countryName;

    @Column(nullable = false)
    private String currency;

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
//@ManyToOne
    //@JoinColumn(name="country_id", referencedColumnName = "id")
    //private Country country;

    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
    private List<City> cities;

    public Country() {
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
