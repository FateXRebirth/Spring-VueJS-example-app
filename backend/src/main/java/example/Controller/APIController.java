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

    @PostMapping("/api/cars")
    public Result getCars(@RequestBody CarSearch carSearch) {
        return carService.getCars(carSearch);
    }

    @GetMapping("/api/cars/{ID}")
    public Result getCarDetailByID(@PathVariable("ID") int CarID) {
        return carService.getCarDetailByID(CarID);
    }
    
    // Private

    @GetMapping("/cars")
    public Result getCars(@RequestHeader("ID") int MemberID) {
        return carService.getCarByOwner(MemberID);
    }

    @PostMapping("/cars")
    public Result create(@RequestHeader("ID") int MemberID, @RequestBody Car car) {
        return carService.create(MemberID, car);
    }

    @GetMapping("/cars/{ID}")
    public Result getCarByID(@PathVariable("ID") int CarID) {
        return carService.getCarByID(CarID);
    }

    @PutMapping("/cars/{ID}")
    public Result editCarByID(@PathVariable("ID") int CarID, @RequestBody Car car) {
        return carService.editCarByID(CarID, car);
    }

    @PostMapping("/cars/{ID}")
    public Result updateStatusByID(@PathVariable("ID") int CarID, @RequestParam("Status") int status) {
        return carService.updateStatusByID(CarID, status);
    }

    @DeleteMapping("/cars/{ID}")
    public Result deleteCarByID(@PathVariable("ID") int CarID) {
        return carService.deleteCaByID(CarID);
    }

    @GetMapping("/users")
    public Result getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{ID}")
    public Result getUserByID(@PathVariable("ID") int MemberID) {
        return userService.getUserByID(MemberID);
    }

    @PutMapping("/users/edit/{ID}")
    public Result editUserByID(@PathVariable("ID") int MemberID, @RequestBody MemberEdit memberEdit) {
        return userService.editUserByID(MemberID, memberEdit);
    }

    @PutMapping("/users/upgrade/{ID}")
    public Result upgradeUserByID(@PathVariable("ID") int MemberID, @RequestBody MemberUpgrade memberUpgrade) {
        return userService.upgradeUserByID(MemberID, memberUpgrade);
    }

    @GetMapping("/users/favorite/{ID}")
    public Result getFavoriteCars(@PathVariable("ID") int MemberID) {
        return userService.getFavoriteCars(MemberID);
    }

    @PostMapping("/users/favorite/{ID}")
    public Result addFavoriteCars(@RequestHeader("ID") int MemberID, @PathVariable("ID") int CarID) {
        return userService.addFavoriteCars(MemberID, CarID);
    }

    @DeleteMapping("/users/favorite/{ID}")
    public Result removeFavoriteCars(@RequestHeader("ID") int MemberID, @PathVariable("ID") int CarID) {
        return userService.removeFavoriteCars(MemberID, CarID);
    }

}
