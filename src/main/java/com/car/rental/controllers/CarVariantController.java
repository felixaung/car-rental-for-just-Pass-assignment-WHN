package com.car.rental.controllers;

import com.car.rental.models.CarVariant;
import com.car.rental.services.CarVariantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CarVariantController {

    private final CarVariantService carVariantService;

    public CarVariantController(CarVariantService carVariantService) {
        this.carVariantService = carVariantService;
    }

    @GetMapping("/admin/car-variant")
    public String carVariant(Model model) {
        model.addAttribute("carVariant", carVariantService.getAll());
        return "admin/car-variants-index";
    }

    @GetMapping("/admin/car-variant/creation")
    public String carVariantCreation(Model model) {
        model.addAttribute("carVariant", new CarVariant());
        return "admin/car-variants-creation";
    }

    @PostMapping("/admin/car-variant-create")
    public String carVariantCreate(@ModelAttribute CarVariant carVariant, Model model){

        carVariantService.saveCarVariant(carVariant);
        return "admin/car-variants-creation";
    }


}
