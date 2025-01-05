package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Attraction;
import softuni.exam.models.entity.Country;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    Optional<Attraction> findByName(String name);

    List<Attraction> findAllByTypeInAndElevationGreaterThanEqualOrderByNameAscCountryNameAsc(List<String> types, int elevation);


}
