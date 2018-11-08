package example.Service;


import example.Entity.Brand;
import example.Entity.Car;
import example.Entity.Model;
import example.Repository.BrandRepository;
import example.Repository.CarRepository;
import example.Repository.ModelRepository;
import example.Repository.YearRepository;
import example.Response.BrandResponse;
import example.Response.ModelResponse;
import example.Response.YearResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    YearRepository yearRepository;

    public List<YearResponse> getYears() {
        return yearRepository.findYearsDetail();
    }

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

    public Model getModelByNameAndID(int id, String name) {
        return modelRepository.findByNameAndID(id, name);
    }

    public List<ModelResponse> getModels() {
        return modelRepository.findModelsDetail();
    }

    public List<ModelResponse> getModelsByBrandID(int id) {
        return modelRepository.findAllByBrandID(id);
    }

    @Autowired
    CarRepository carRepository;

    public void CreateCar(Car car) {
        carRepository.save(car);
    }

    public Car getCarBySpecification(int brandID, int modelID, int yearID) {
        return carRepository.findBySpecification(brandID, modelID, yearID);
    }

}
