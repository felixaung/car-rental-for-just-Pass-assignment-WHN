package com.car.rental.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {

    public PaymentController(){

    }

    @GetMapping("/admin/payments")
    public String paymentIndex(){
        return "admin/payment-index";
    }
}
