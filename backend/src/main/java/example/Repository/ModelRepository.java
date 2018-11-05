package example.Repository;

import example.Entity.Model;
import example.Response.ModelResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer>{

    Model findByName(String name);

    @Query("SELECT new example.Response.ModelResponse(m.brandID, m.name) FROM Model AS m")
    List<ModelResponse> findModelsDetail();
}
