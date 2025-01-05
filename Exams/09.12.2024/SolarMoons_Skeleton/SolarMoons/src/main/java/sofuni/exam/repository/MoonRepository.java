package sofuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sofuni.exam.models.entity.Moon;
import sofuni.exam.models.enums.Type;

import java.util.List;
import java.util.Optional;

@Repository
public interface MoonRepository extends JpaRepository<Moon, Long> {
Optional<Moon> findByName(String name);

//Export ALL moons that orbits planets of type GAS_GIANT
// and have radius between 700km and 2000km. from the Database
    //•	Filter only moons whose radius is more than or equal to 700km and
    // equal to or less than 2000km. Order the results by moon name in ascending order.


List<Moon> findAllByPlanetTypeAndRadiusBetweenOrderByNameAsc(Type type, double first, double second);

    List<Moon> findAllByPlanetTypeAndRadiusGreaterThanEqualAndRadiusLessThanEqualOrderByNameAsc(Type type, double first, double second);

    List<Moon> findAllByRadiusGreaterThanEqualAndRadiusLessThanEqualAndPlanetTypeOrderByNameAsc(double first, double second, Type type);

    List<Moon> findAllByOrderByNameAsc();

    List<Moon> findAllByPlanetTypeOrderByNameAsc(Type type);

    List<Moon> findAllByRadiusBetweenOrderByNameAsc(double first, double second);

}
