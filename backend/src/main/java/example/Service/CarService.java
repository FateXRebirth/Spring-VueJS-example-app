package example.Service;


import example.Entity.Brand;
import example.Repository.BrandRepository;
import example.Response.BrandResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    BrandRepository brandRepository;

    public void CreateBrand(Brand brand) {
        brandRepository.save(brand);
    }

    public Brand getBrandByName(String name) {
        return brandRepository.findByName(name);
    }

    public List<BrandResponse> getBrands() {
        return brandRepository.findBrandsDetail();
    }
}
