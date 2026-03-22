package com.car.rental.controllers;

import com.car.rental.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {

    private final UserService userService;

    public CustomerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/customers")
    public String customersPage(){
        return "admin/customers-index";
    }
}
