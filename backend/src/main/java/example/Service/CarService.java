package example.Service;


import example.Entity.Brand;
import example.Repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    BrandRepository brandRepository;

    public void CreateBrand(Brand brand) {
        brandRepository.save(brand);
    }
}
