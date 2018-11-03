package example.Service;


import example.Entity.Brand;
import example.Entity.Model;
import example.Repository.BrandRepository;
import example.Repository.ModelRepository;
import example.Response.BrandResponse;
import example.Response.ModelResponse;
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

    @Autowired
    ModelRepository modelRepository;

    public void CreateModel(Model model) {
        modelRepository.save(model);
    }

    public Model getModelByName(String name) {
        return modelRepository.findByName(name);
    }

    public List<ModelResponse> getModels() {
        return modelRepository.findModelsDetail();
    }
}
