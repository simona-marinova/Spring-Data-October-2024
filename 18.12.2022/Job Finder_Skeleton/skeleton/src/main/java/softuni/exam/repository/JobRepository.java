package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Job;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    //•	Filter only jobs where the salary is greater than or equal to 5000.00 and
    // hours a week are lesser than or equal to 30.00 and order them by the salary in descending order.

    List<Job> findAllBySalaryGreaterThanEqualAndHoursAWeekLessThanEqualOrderBySalaryDesc(double salary, double hours);

}
