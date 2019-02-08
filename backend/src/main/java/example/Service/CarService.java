package example.Service;


import example.Repository.CarRepository;
import example.Response.Cars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public List<Cars> getCars() {
        return carRepository.getCars();
    }

    public Cars getCarByID(int id) {
        return carRepository.getCarByID(id);
    }

}
