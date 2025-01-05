package softuni.exam.models.entity;

import org.springframework.boot.autoconfigure.batch.BatchProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="companies")
public class Company extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String website;

    @Column(name="date_established", nullable = false)
    private LocalDate dateEstablished;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "companies_jobs", joinColumns = @JoinColumn(name ="company_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="jobs_id", referencedColumnName = "id"))
  private List<Job> jobs;


   @ManyToOne
   @JoinColumn(name="country_id", referencedColumnName = "id")
   private Country country;


    public Company() {
    }


    public List<Job> getJobs() {return jobs;
    }

    public void setJobs(List<Job> jobs) {
       this.jobs = jobs;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public LocalDate getDateEstablished() {
        return dateEstablished;
    }

    public void setDateEstablished(LocalDate dateEstablished) {
        this.dateEstablished = dateEstablished;
    }
}
