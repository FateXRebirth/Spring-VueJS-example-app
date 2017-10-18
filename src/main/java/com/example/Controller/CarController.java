package com.example.Controller;

import com.example.Entity.Car;
import com.example.Service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by FateXRebirth on 18/10/2017.
 */

@Controller
public class CarController {

    @Autowired
    private ICarService carService;

    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("/showCars")
    public ModelAndView showCars() {
        List<Car> cars = carService.findAll();

        Map<String, Object> params = new HashMap<>();
        params.put("cars", cars);

        return new ModelAndView("showCars", params);
    }
}