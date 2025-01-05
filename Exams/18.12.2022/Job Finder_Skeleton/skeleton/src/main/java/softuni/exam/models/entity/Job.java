package softuni.exam.models.entity;


import javax.persistence.*;

@Entity
@Table(name = "jobs")
public class Job extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private double salary;

    @Column(name = "hoursaweek", nullable = false)
    private double hoursAWeek;

    @Column(nullable = false)
    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name="company_id", referencedColumnName = "id")
    private Company company;

    public Job() {
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getHoursAWeek() {
        return hoursAWeek;
    }

    public void setHoursAWeek(double hoursAWeek) {
        this.hoursAWeek = hoursAWeek;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
