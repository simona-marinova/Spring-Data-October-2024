package softuni.exam.models.entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name="forecasts")
public class Forecast extends BaseEntity{


    @Column(name="day_of_week", nullable = false)
    @Enumerated(EnumType.STRING)
private DayOfWeek dayOfWeek;

    @Column(name="max_temperature", nullable = false)
private double maxTemperature;


    @Column(name="min_temperature", nullable = false)
private double minTemperature;

    @Column(nullable = false)
    private Time sunrise;

    @Column(nullable = false)
    private Time sunset;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    //relation with cities
    @ManyToOne
    @JoinColumn(name="city_id", referencedColumnName = "id")
    private City city;

    public Forecast() {
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public Time getSunrise() {
        return sunrise;
    }

    public void setSunrise(Time sunrise) {
        this.sunrise = sunrise;
    }

    public Time getSunset() {
        return sunset;
    }

    public void setSunset(Time sunset) {
        this.sunset = sunset;
    }
}
