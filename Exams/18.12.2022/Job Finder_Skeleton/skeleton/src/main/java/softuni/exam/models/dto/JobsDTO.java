package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="jobs")
@XmlAccessorType(XmlAccessType.FIELD)
public class JobsDTO {

    @XmlElement(name="job")
    private List<JobDTO> jobs;

    public JobsDTO(List<JobDTO> jobs) {
        this.jobs = jobs;
    }

    public JobsDTO() {
    }

    public List<JobDTO> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobDTO> jobs) {
        this.jobs = jobs;
    }
}
