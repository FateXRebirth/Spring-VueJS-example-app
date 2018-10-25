package example.Service;


import example.Entity.Brand;
import example.Repository.BrandRepository;
import example.Repository.CarRepository;
import example.Repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarService {

    @Autowired
    BrandRepository brandRepository;
    ModelRepository modelRepository;
    CarRepository carRepository;

    public void CreateBrand(Brand brand) {
        brandRepository.save(brand);
    }

    public List<Brand> GetBrands() {
        return brandRepository.findAll();
    }
}
