package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="forecast")
@XmlAccessorType(XmlAccessType.FIELD)
public class ForecastDTO {

    //<forecast>
    //        <day_of_week>NULL</day_of_week>
    //        <max_temperature>25</max_temperature>
    //        <min_temperature>-5</min_temperature>
    //        <sunrise>06:12:09</sunrise>
    //        <sunset>21:19:52</sunset>
    //        <city>1</city>
    //    </forecast>

    @XmlElement(name="day_of_week")
    private String dayOfWeek;

    @XmlElement(name="max_temperature")
    private double maxTemperature;

    @XmlElement(name="min_temperature")
    private double minTemperature;

    @XmlElement
    private String sunrise;

    @XmlElement
    private String sunset;

    @XmlElement
    private int city;

    public ForecastDTO() {
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
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

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }
}
