package com.car.rental.controllers;

import com.car.rental.models.User;
import com.car.rental.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/auth/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @GetMapping("/auth/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "auth/login";
    }

    @PostMapping("/auth/registration")
    public String registration(@ModelAttribute("user") User user, Model model) {

        try {
            userService.save(user.getEmail(), user.getUsername(), user.getPassword());
            return "redirect:/auth/login";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "auth/register";
        }

    }
}
