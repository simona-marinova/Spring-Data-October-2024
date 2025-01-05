package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.JobDTO;
import softuni.exam.models.dto.JobsDTO;
import softuni.exam.models.entity.Job;
import softuni.exam.repository.CompanyRepository;
import softuni.exam.repository.JobRepository;
import softuni.exam.service.JobService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private static final String FILE_PATH = "C:\\Users\\stamb\\Desktop\\exams\\18.12.2022\\Job Finder_Skeleton\\skeleton\\src\\main\\resources\\files\\xml\\jobs.xml";
    private final JobRepository jobRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final CompanyRepository companyRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.companyRepository = companyRepository;
    }

    @Override
    public boolean areImported() {
        return this.jobRepository.count() > 0;
    }

    @Override
    public String readJobsFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importJobs() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        JobsDTO jobsDTO = this.xmlParser.fromFile(FILE_PATH, JobsDTO.class);
        for (JobDTO jobDTO : jobsDTO.getJobs()) {
            if (!validationUtil.isValid(jobDTO)) {
                sb.append("Invalid job").append(System.lineSeparator());
                continue;
            }

            Job job = this.modelMapper.map(jobDTO, Job.class);
            job.setCompany(companyRepository.findById((long) jobDTO.getCompanyId()).get());
            jobRepository.saveAndFlush(job);
            sb.append(String.format("Successfully imported job %s", jobDTO.getTitle())).append(System.lineSeparator());
        }

        return sb.toString();
    }

    @Override
    public String getBestJobs() {
        StringBuilder sb = new StringBuilder();
        List<Job> jobs = this.jobRepository.findAllBySalaryGreaterThanEqualAndHoursAWeekLessThanEqualOrderBySalaryDesc(5000.00, 30.00);
        for (Job job : jobs) {
            sb.append(String.format("Job title %s\n" +
                                    "-Salary: %.2f$\n" +
                                    "      --Hours a week: %.2fh.\n", job.getTitle(),
                            job.getSalary(), job.getHoursAWeek()))
                    .append(System.lineSeparator());
        }


        return sb.toString();

    }
}
