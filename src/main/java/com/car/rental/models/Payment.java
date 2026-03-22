package com.car.rental.models;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Instant return_date;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus payment_status;

    @Column(nullable = true)
    private Instant payment_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "verified_by")
    private User verifiedBy;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    public Payment() {
    }

    public Payment(Long id, Instant return_date, BigDecimal amount, PaymentStatus payment_status, Instant payment_date, User verifiedBy, Booking booking) {
        this.id = id;
        this.return_date = return_date;
        this.amount = amount;
        this.payment_status = payment_status;
        this.payment_date = payment_date;
        this.verifiedBy = verifiedBy;
        this.booking = booking;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Instant return_date) {
        this.return_date = return_date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentStatus getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(PaymentStatus payment_status) {
        this.payment_status = payment_status;
    }

    public Instant getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Instant payment_date) {
        this.payment_date = payment_date;
    }

    public User getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(User verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
