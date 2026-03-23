package com.car.rental.controllers;

import com.car.rental.services.BookingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller

public class BookingController {

    private final BookingService bookingService;


    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/admin/bookings")
    public String bookingIndex(Model model){
        model.addAttribute("bookings", bookingService.findAll());
        model.addAttribute("recentBookings",bookingService.findAllPending());
        return "admin/booking-index";
    }


    @PostMapping("/user/bookings")
    public String saveBooking(
            @RequestParam("carId") Long carId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @AuthenticationPrincipal UserDetails userDetails,
            RedirectAttributes redirectAttributes
    ) {

        try {
            bookingService.save(userDetails.getUsername(), carId, startDate, endDate);
            redirectAttributes.addFlashAttribute("successMessage", "Booking created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/user/cars";
    }

    @PostMapping("/admin/accept-booking")
    public String acceptBooking(@RequestParam("bookingId") Long bookingId) {
        bookingService.acceptBookings(bookingId);
        return "redirect:/admin/bookings";
    }

    @PostMapping("/admin/reject-booking")
    public String rejectBooking(@RequestParam("bookingId") Long bookingId) {
        bookingService.rejectBookings(bookingId);
        return "redirect:/admin/bookings";
    }

}
