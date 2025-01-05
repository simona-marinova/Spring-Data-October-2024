package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="forecasts")
@XmlAccessorType(XmlAccessType.FIELD)
public class ForecastsDTO {

    @XmlElement(name="forecast")
    private List<ForecastDTO> forecasts;

    public ForecastsDTO() {
    }

    public ForecastsDTO(List<ForecastDTO> forecasts) {
        this.forecasts = forecasts;
    }

    public List<ForecastDTO> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<ForecastDTO> forecasts) {
        this.forecasts = forecasts;
    }
}
