package example.Repository;

import example.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findById(Integer id);

    Person findByUsername(String username);

    @Query("SELECT username FROM Person")
    List<String> findAllUsername();

    @Query("SELECT email FROM Person")
    List<String> findAllEmail();
}