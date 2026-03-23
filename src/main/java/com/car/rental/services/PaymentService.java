package com.car.rental.services;

import com.car.rental.models.Booking;
import com.car.rental.models.BookingStatus;
import com.car.rental.models.Payment;
import com.car.rental.models.PaymentStatus;
import com.car.rental.repositories.BookingRepository;
import com.car.rental.repositories.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    public PaymentService(PaymentRepository paymentRepository,BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public void  save(Payment payment) {
        paymentRepository.save(payment);
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Payment not found"));
    }

    @Transactional
    public void deleteById(Long id) {
        paymentRepository.deleteById(id);
    }

    public void payForUserBooking(Long bookingId, String userEmail) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getCustomer().getEmail().equals(userEmail)) {
            throw new RuntimeException("Unauthorized access");
        }

        Payment payment = booking.getPayment();

        if (payment == null) {
            payment = new Payment();
            payment.setBooking(booking);
            payment.setAmount(booking.getTotal_amount());
            payment.setReturn_date(booking.getReturn_date());
        }

        payment.setPayment_status(PaymentStatus.PAID);
        payment.setPayment_date(Instant.now());

        paymentRepository.save(payment);
        booking.setPayment(payment);
        bookingRepository.save(booking);
    }

    @Transactional
    public void cancelUserBooking(Long bookingId, String userEmail) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getCustomer().getEmail().equals(userEmail)) {
            throw new RuntimeException("Unauthorized access");
        }

        if (booking.getPayment() != null &&
                booking.getPayment().getPayment_status() == PaymentStatus.PAID) {
            throw new RuntimeException("Paid booking cannot be cancelled");
        }

        booking.setStatus(BookingStatus.Cancelled);
        bookingRepository.save(booking);
    }


}
