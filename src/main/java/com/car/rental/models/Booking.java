package com.car.rental.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;


    @Column(nullable = true)
    private Instant start_date;

    @Column(nullable = true)
    private Instant end_date;

    @Column(nullable = true)
    private Instant return_date;

    @Column(nullable = true)
    private String rejection_reason;

    @Column(nullable = true)
    private BigDecimal total_amount;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne
    @JoinColumn(name = "approved_by", nullable = true)
    private User approvedBy;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment;

    public Booking() {
    }

    public Booking(Long id, BookingStatus status, Instant start_date, Instant end_date, Instant return_date, String rejection_reason, BigDecimal total_amount, User customer, User approvedBy, Car car, Payment payment) {
        this.id = id;
        this.status = status;
        this.start_date = start_date;
        this.end_date = end_date;
        this.return_date = return_date;
        this.rejection_reason = rejection_reason;
        this.total_amount = total_amount;
        this.customer = customer;
        this.approvedBy = approvedBy;
        this.car = car;
        this.payment = payment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Instant getStart_date() {
        return start_date;
    }

    public void setStart_date(Instant start_date) {
        this.start_date = start_date;
    }

    public Instant getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Instant end_date) {
        this.end_date = end_date;
    }

    public Instant getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Instant return_date) {
        this.return_date = return_date;
    }

    public String getRejection_reason() {
        return rejection_reason;
    }

    public void setRejection_reason(String rejection_reason) {
        this.rejection_reason = rejection_reason;
    }

    public BigDecimal getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(BigDecimal total_amount) {
        this.total_amount = total_amount;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(User approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
