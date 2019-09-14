package example.Service;

import example.Repository.*;
import example.Request.Car;
import example.Request.CarSearch;
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

    public Result getCars(CarSearch carSearch) { return  carRepository.getCars(carSearch); }

    public Result getCarDetailByID(int CarID) { return carRepository.getCarDetailByID(CarID); }

    public Result create(int MemberID, Car car) { return carRepository.create(MemberID, car); }

    public Result getCarByOwner(int MemberID) { return carRepository.getCarByOwner(MemberID); }

    public Result getCarByID(int CarID) { return carRepository.getCarByID(CarID); }

    public Result editCarByID(int CarID, Car car) { return carRepository.editCarByID(CarID, car); }

    public Result deleteCaByID(int CarID) { return carRepository.deleteCarByID(CarID); }

    public Result updateStatusByID(int CarID, int status) { return  carRepository.updateStatusByID(CarID, status); }

}
