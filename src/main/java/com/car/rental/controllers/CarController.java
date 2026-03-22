package com.car.rental.controllers;

import com.car.rental.models.Car;
import com.car.rental.models.CarStatus;
import com.car.rental.services.CarService;
import com.car.rental.services.CarVariantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CarController {

    private final CarService carService;
    private final CarVariantService carVariantService;

    public CarController(CarService carService, CarVariantService carVariantService) {

        this.carVariantService = carVariantService;
        this.carService = carService;
    }

    @GetMapping("/admin/cars")
    public String adminCarPage(Model model){
        model.addAttribute("cars", carService.findAll());
        return "admin/cars-index";
    }

    @GetMapping("/user/cars")
    public String userCarPage(Model model){
        model.addAttribute("cars", carService.findAllBookable());
        return "user/cars-index";
    }

    @GetMapping("/admin/cars/creation")
    public String carCreationPage(Model model){
        model.addAttribute("car", new Car());
        model.addAttribute("carVariant", carVariantService.getAll());
        model.addAttribute("status", CarStatus.values());
        return "admin/car-creation";
    }

    @PostMapping("/admin/cars-create")
    public String carCreate(@ModelAttribute Car car, Model model){
        carService.save(car);
        return  "redirect:/admin/cars";
    }
}
