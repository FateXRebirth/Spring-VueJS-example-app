package example.Controller;
import example.Request.*;
import example.Request.Car;
import example.Response.*;
import example.Service.CarService;
import example.Service.CommonService;
import example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by FateXRebirth on 20/10/2017.
 */

@RestController
public class APIController {

    @Autowired
    CarService carService;

    @Autowired
    UserService userService;

    @Autowired
    CommonService commonService;

    // Public

    @GetMapping("/api/brand")
    public Result getBrands() {
        return carService.getBrands();
    }

    @GetMapping("/api/series")
    public Result getSeries() {
        return carService.getSeries();
    }

    @GetMapping("/api/category")
    public Result getCategories() {
        return carService.getCategories();
    }

    @PostMapping("/api/login")
    public Result userLogin(@RequestBody MemberLogin memberLogin) {
        return userService.login(memberLogin);
    }

    @PostMapping("/api/register")
    public Result userRegister(@RequestBody MemberRegister memberRegister) {
        return userService.register(memberRegister);
    }

    @GetMapping("/api/news")
    public Result getNews() {
        return commonService.getNews();
    }

    @GetMapping("/api/banner")
    public Result getBanner() {
        return commonService.getBanner();
    }

    @GetMapping("/api/region")
    public Result getRegion() {
        return commonService.getRegion();
    }

    @GetMapping("/api/specification")
    public Result getSpecification() {
        return commonService.getSpecification();
    }

    @GetMapping("/api/cars/{id}")
    public Result getCar(@PathVariable int id) {
        return carService.getCarByID(id);
    }

    // Private

    @GetMapping("/cars")
    public Result getCars(@RequestHeader("ID") int ID) {
        return carService.getCarByOwner(ID);
    }

    @PostMapping("/cars")
    public Result create(@RequestHeader("ID") int ID, @RequestBody Car car) {
        return carService.create(ID, car);
    }

    @GetMapping("/cars/{id}")
    public Result getCarByID(@PathVariable int id) {
        return carService.getCarByID(id);
    }

    @PutMapping("/cars/{id}")
    public Result editCarByID(@PathVariable int id, @RequestBody Car car) {
        return carService.editCarByID(id, car);
    }

    @GetMapping("/cars/add/{id}")
    public Result addCar(@PathVariable int id) {
        return carService.addCar(id);
    }

    @GetMapping("/cars/remove/{id}")
    public Result removeCar(@PathVariable int id) {
        return carService.removeCar(id);
    }

    @DeleteMapping("/cars/delete/{id}")
    public Result deleteCar(@PathVariable int id) {
        return carService.deleteCar(id);
    }

    @GetMapping("/users")
    public Result getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public Result getUserByID(@PathVariable("id") int id) {
        return userService.getUserByID(id);
    }

    @PutMapping("/users/edit/{id}")
    public Result editUserByID(@PathVariable("id") int id, @RequestBody MemberEdit memberEdit) {
        return userService.editUserByID(id, memberEdit);
    }

    @PutMapping("/users/upgrade/{id}")
    public Result upgradeUserByID(@PathVariable("id") int id, @RequestBody MemberUpgrade memberUpgrade) {
        return userService.upgradeUserByID(id, memberUpgrade);
    }

    @GetMapping("/users/favorite/{id}")
    public Result getFavoriteCars(@PathVariable int id) {
        return userService.getFavoriteCars(id);
    }

    @PostMapping("/users/favorite")
    public Result createFavoriteCars(@RequestBody FavoriteCar favoriteCar) {
        return userService.createFavoriteCars(favoriteCar);
    }

    @DeleteMapping("/users/favorite")
    public Result DeleteFavoriteCars(@RequestBody FavoriteCar favoriteCar) {
        return userService.DeleteFavoriteCars(favoriteCar);
    }

}
