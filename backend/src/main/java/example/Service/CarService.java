package example.Service;

import example.Repository.*;
import example.Request.Car;
import example.Response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    SeriesRepository seriesRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public Result getBrands() {
        return brandRepository.getBrands();
    }

    public Result getSeries() { return seriesRepository.getSeries(); }

    public Result getCategories() { return categoryRepository.getCategories(); }

    public Result getCars() { return  carRepository.getCars(); }

    public Result getCarDetailByID(int id) { return carRepository.getCarDetailByID(id); }

    public Result create(int id, Car car) {
        return carRepository.create(id, car);
    }

    public Result getCarByOwner(int id) { return carRepository.getCarByOwner(id); }

    public Result getCarByID(int id) { return carRepository.getCarByID(id); }

    public Result editCarByID(int id, Car car) {
        return carRepository.editCarByID(id, car);
    }

    public Result deleteCaByID(int id) { return carRepository.deleteCarByID(id); }

    public Result updateStatusByID(int id, int status) { return  carRepository.updateStatusByID(id, status); }

}
