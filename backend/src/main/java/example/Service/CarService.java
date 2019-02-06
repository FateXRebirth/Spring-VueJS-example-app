package example.Service;


import example.Entity.Car;
import example.Repository.CarRepository;
import example.Response.CarDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public List<Car> getCars() {
        return carRepository.getCars();
    }

    public List<CarDetail> getCarsDetail() {
        return carRepository.getCarsDetail();
    }

}
