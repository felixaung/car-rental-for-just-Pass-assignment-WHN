package com.car.rental.controllers;


import com.car.rental.models.Booking;
import com.car.rental.models.Payment;
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



    @GetMapping("/user/payments")
    public String userPaymentIndex(Authentication authentication, Model model) {
        String email = authentication.getName(); // usually logged-in user's email
        List<Booking> bookings = bookingService.findAllByCustomerEmail(email);

        model.addAttribute("bookings", bookings);
        return "user/payment-index";
    }

    @GetMapping("/admin/payment")
    public String adminPayment(Model model){
        List<Payment> payments = paymentService.findAllWithDetails();

        System.out.println("payments size = " + payments.size());
        for (Payment p : payments) {
            System.out.println("payment id = " + p.getId()
                    + ", amount = " + p.getAmount()
                    + ", status = " + p.getPayment_status()
                    + ", booking = " + (p.getBooking() != null ? p.getBooking().getId() : null));
        }
        model.addAttribute("payments", paymentService.findAllWithDetails());
        return "admin/payment-index";
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
