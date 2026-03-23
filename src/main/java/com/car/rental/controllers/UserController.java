package com.car.rental.controllers;


import com.car.rental.models.User;
import com.car.rental.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/users")
    public String usersList(Model model){
        model.addAttribute("users", userService.findAll());
        List<User> users = userService.findAll();
        System.out.println("users size = " + users.size());
        return "admin/customers-index";
    }
}
