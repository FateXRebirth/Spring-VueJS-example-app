package example.Repository;

import example.Entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{

    @Query("SELECT c FROM Car AS c WHERE c.brandID = :brandID AND c.modelID = :modelID AND c.yearID = :yearID")
    Car findBySpecification(@Param("brandID") int brandID, @Param("modelID") int modelID, @Param("yearID") int yearID);
}
