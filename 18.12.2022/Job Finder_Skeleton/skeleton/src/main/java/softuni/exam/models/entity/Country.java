package softuni.exam.models.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="countries")
public class Country extends BaseEntity{

    @Column(unique = true, nullable = false)
private String name;

    @Column(nullable = false, unique = true)
private String code;

    @Column(nullable = false)
    private String currency;


@OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
private List<Company> companies;

    public Country() {
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
