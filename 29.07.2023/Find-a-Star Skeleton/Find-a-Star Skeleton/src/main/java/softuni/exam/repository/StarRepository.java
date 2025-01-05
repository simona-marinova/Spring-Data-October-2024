package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Star;
import softuni.exam.models.entity.StarType;

import java.util.List;
import java.util.Optional;

@Repository
public interface StarRepository extends JpaRepository<Star, Long> {

    Optional<Star> findByName(String name);

//•	Extract from the database, the star name, distance in light years
// (to second digit after decimal point), description and the constellation name.
//•	Filter only stars who are Red Giants and have never been observed and
// order them by the light years in ascending order.

    List<Star> findAllByStarTypeAndObserversEmptyOrderByLightYearsAsc(StarType startype);


}
