package example.Controller;

import example.Entity.Brand;
import example.Entity.Car;
import example.Entity.Model;
import example.Entity.Person;

import example.Request.BrandRequest;
import example.Request.CarRequest;
import example.Request.ModelRequest;
import example.Request.RegisterRequest;

import example.Response.BrandResponse;
import example.Response.ModelResponse;
import example.Response.YearResponse;
import example.Service.CarService;
import example.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by FateXRebirth on 20/10/2017.
 */

@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    HttpSession session;

    @Autowired
    PersonService personService;

    @GetMapping("/get/person/{id}")
    public Person getPersonByID(@PathVariable int id) {
        return personService.getPersonById(id);
    }

    @GetMapping("/get/person")
    public Person getPersonByUsername(@RequestParam String username) {
        return personService.getPersonByUsername(username);
    }

    @GetMapping("/person/GetAllPerson")
    public List<Person> getPersons() {
        return personService.getPersons();
    }

    @GetMapping("/person/GetAllPersonEmail")
    public List<String> getEmail() {
        return personService.getAllEmail();
    }

    @PostMapping("/person/Create")
    public void register(@RequestBody RegisterRequest registerRequest) {
        Person newUser = new Person(registerRequest.getUsername(), registerRequest.getPassword(), registerRequest.getEmail(), registerRequest.getType());
        personService.create(newUser);
    }

    @Autowired
    CarService carService;

    @GetMapping("/brand/GetAllBrand")
    public List<BrandResponse> getBrands() {
        return carService.getBrands();
    }

    @PostMapping("/brand/Create")
    public int brand(@RequestBody BrandRequest brandRequest) {
        if(carService.getBrandByName(brandRequest.getName()) == null) {
            carService.CreateBrand(new Brand(brandRequest.getName()));
            return 0;
        } else {
            return 1;
        }
    }

    @GetMapping("/model/GetAllModel")
    public List<ModelResponse> getModels() {
        return carService.getModels();
    }

    @PostMapping("/model/GetAllModelByBrandID")
    public List<ModelResponse> getModelsByBrandID(@RequestBody ModelRequest modelRequest) {
        return carService.getModelsByBrandID(modelRequest.getBrandID());
    }

    @PostMapping("/model/Create")
    public int model(@RequestBody ModelRequest modelRequest) {
        try {
            if(carService.getModelByNameAndID(modelRequest.getBrandID(), modelRequest.getName()) == null) {
                carService.CreateModel(new Model(modelRequest.getBrandID(), modelRequest.getName()));
                return 0;
            } else {
                return  1;
            }
        } catch (IncorrectResultSizeDataAccessException e) {
            System.out.println(e);
            return  1;
        }
    }

    @GetMapping("/year/GetAllYear")
    public List<YearResponse> getYears() {
        return carService.getYears();
    }

    @PostMapping("/car/Create")
    public int car(@RequestBody CarRequest carRequest) {
        if(carService.getCarBySpecification(carRequest.getBrandID(), carRequest.getModelID(), carRequest.getYearID()) == null) {
            carService.CreateCar(new Car(carRequest.getBrandID(), carRequest.getModelID(), carRequest.getYearID(), session.getAttribute("uid").toString()));
            return 0;
        } else {
            return 1;
        }
    }

}
