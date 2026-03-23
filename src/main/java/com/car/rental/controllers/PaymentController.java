package com.car.rental.controllers;


import com.car.rental.models.Booking;
import com.car.rental.services.BookingService;
import com.car.rental.services.PaymentService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PaymentController {

    private final BookingService bookingService;
    private final PaymentService paymentService;

    public PaymentController(BookingService bookingService,PaymentService paymentService) {
        this.bookingService = bookingService;
        this.paymentService = paymentService;

    }

    @GetMapping("/admin/payments")
    public String paymentIndex(){
        return "admin/payment-index";
    }

    @GetMapping("/user/payments")
    public String userPaymentIndex(Authentication authentication, Model model) {
        String email = authentication.getName(); // usually logged-in user's email
        List<Booking> bookings = bookingService.findAllByCustomerEmail(email);

        model.addAttribute("bookings", bookings);
        return "user/payment-index";
    }

    @PostMapping("/user/payments/{bookingId}/pay")
    public String payBooking(@PathVariable Long bookingId, Authentication authentication) {
        paymentService.payForUserBooking(bookingId, authentication.getName());
        return "redirect:/user/payments";
    }

    @PostMapping("/user/payments/{bookingId}/cancel")
    public String cancelBooking(@PathVariable Long bookingId, Authentication authentication) {
        paymentService.cancelUserBooking(bookingId, authentication.getName());
        return "redirect:/user/payments";
    }

    @PostMapping("/user/payments/{bookingId}/return")
    public String returnCar(@PathVariable Long bookingId, Authentication authentication) {
        bookingService.returnUserBooking(bookingId, authentication.getName());
        return "redirect:/user/payments";
    }
}
