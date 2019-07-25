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

    @GetMapping("/api/cars")
    public Result getCars() {
        return carService.getCars();
    }

    @GetMapping("/api/cars/{ID}")
    public Result getCarDetailByID(@PathVariable("ID") int ID) {
        return carService.getCarDetailByID(ID);
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

    @GetMapping("/cars/{ID}")
    public Result getCarByID(@PathVariable("ID") int ID) {
        return carService.getCarByID(ID);
    }

    @PutMapping("/cars/{ID}")
    public Result editCarByID(@PathVariable("ID") int ID, @RequestBody Car car) {
        return carService.editCarByID(ID, car);
    }

    @PostMapping("/cars/{ID}")
    public Result updateStatusByID(@PathVariable("ID") int ID, @RequestParam("Status") int status) {
        return carService.updateStatusByID(ID, status);
    }

    @DeleteMapping("/cars/{ID}")
    public Result deleteCarByID(@PathVariable("ID") int ID) {
        return carService.deleteCaByID(ID);
    }

    @GetMapping("/users")
    public Result getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{ID}")
    public Result getUserByID(@PathVariable("ID") int ID) {
        return userService.getUserByID(ID);
    }

    @PutMapping("/users/edit/{id}")
    public Result editUserByID(@PathVariable("ID") int ID, @RequestBody MemberEdit memberEdit) {
        return userService.editUserByID(ID, memberEdit);
    }

    @PutMapping("/users/upgrade/{ID}")
    public Result upgradeUserByID(@PathVariable("ID") int ID, @RequestBody MemberUpgrade memberUpgrade) {
        return userService.upgradeUserByID(ID, memberUpgrade);
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
