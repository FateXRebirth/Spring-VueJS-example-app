package example.Repository;

import example.Entity.Year;
import example.Response.YearResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface YearRepository extends JpaRepository<Year, Integer> {

    @Query("SELECT new example.Response.YearResponse(y.id, y.year) FROM Year AS y")
    List<YearResponse> findYearsDetail();
}
