package example.Repository;

import example.Entity.Model;
import example.Response.ModelResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer>{

    Model findByName(String name);

    @Query("SELECT m FROM Model AS m WHERE m.brandID = :id AND m.name =:name")
    Model findByNameAndID(@Param("id") int id, @Param("name") String name);

    @Query("SELECT new example.Response.ModelResponse(m.id, m.name) FROM Model AS m WHERE m.brandID = :id")
    List<ModelResponse> findAllByBrandID(@Param("id") int id);

    @Query("SELECT new example.Response.ModelResponse(m.id, m.name) FROM Model AS m")
    List<ModelResponse> findModelsDetail();
}
