package example.Repository;

import example.Entity.Brand;
import example.Response.BrandResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

    Brand findByName(String name);

    @Query("SELECT new example.Response.BrandResponse(b.id, b.name) FROM Brand AS b")
    List<BrandResponse> findBrandsDetail();
}
