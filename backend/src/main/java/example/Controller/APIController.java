package example.Controller;

import example.Entity.Person;

import example.Request.RegisterRequest;

import example.Response.Cars;
import example.Service.CarService;
import example.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/cars")
    public List<Cars> getCars() {
        return carService.getCars();
    }

    @GetMapping("/cars/{id}")
    public Cars getCarByID(@PathVariable int id) {
        return carService.getCarByID(id);
    }

}
