package com.car.rental.services;

import com.car.rental.models.*;
import com.car.rental.repositories.BookingRepository;
import com.car.rental.repositories.CarRepository;
import com.car.rental.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final CarService carService;

    public BookingService(BookingRepository bookingRepository
    , CarRepository carRepository, UserRepository userRepository,CarService carService) {
        this.bookingRepository = bookingRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.carService = carService;
    }

    @Transactional
    public void accpetBookings(Long id){
        Booking booking = bookingRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Booking not found"));
        booking.setStatus(BookingStatus.Approved);
        booking.getCar().setStatus(CarStatus.Rented);

    }

    @Transactional
    public void rejectBookings(Long id){
        Booking booking =  bookingRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Booking not found"));
        booking.setStatus(BookingStatus.Rejected);
        booking.getCar().setStatus(CarStatus.Available);

    }

    @Transactional
    public void save(String email, Long carId, LocalDate startDate, LocalDate endDate){

        Car car = carRepository.findByIdWithVariant(carId).orElseThrow(()->new RuntimeException("Car not found"));

        User customer = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("Customer not found"));
        if (startDate == null || endDate == null) {
            throw new RuntimeException("Start date and end date cannot be null");
        }
        if (startDate.isAfter(endDate)) {
            throw new RuntimeException("Start date cannot be after end date");
        }

        Instant startInstant = toStartOfDay(startDate);
        Instant endInstant = toEndOfDay(endDate);

        List<Booking> conflicts = bookingRepository.findOverlappingBookings(
                car.getId(),
                startInstant,
                endInstant,
                List.of(BookingStatus.Pending, BookingStatus.Approved)
        );

        if (!conflicts.isEmpty()) {
            throw new RuntimeException("This car is already booked for the selected dates");
        }

        long days = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        BigDecimal totalAmount = car.getCarVariant()
                .getPricePerDay()
                .multiply(BigDecimal.valueOf(days));

        Booking booking = new Booking();
        booking.setCar(car);
        booking.setCustomer(customer);
        booking.setStatus(BookingStatus.Pending);
        booking.setStart_date(startInstant);
        booking.setEnd_date(endInstant);
        booking.setTotal_amount(totalAmount);
        bookingRepository.save(booking);

        car.setStatus(CarStatus.Booked);
    }


    public List<Booking> findAll(){
        return bookingRepository.findAll();
    }

    public Booking findById(Long id){
        return bookingRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Booking not found"));
    }


    public List<Booking> findAllPending(){
        return bookingRepository.findAllByBookingStatus(BookingStatus.Pending);
    }


    private Instant toStartOfDay(LocalDate date) {
        return date.atStartOfDay(ZoneId.systemDefault()).toInstant();
    }

    private Instant toEndOfDay(LocalDate date) {
        return date.plusDays(1).atStartOfDay(ZoneId.systemDefault()).minusNanos(1).toInstant();
    }


}
