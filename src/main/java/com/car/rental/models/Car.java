package com.car.rental.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String registration_no;

    @OneToMany(mappedBy = "car")
    private Set<Booking> bookings = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "car_variant_id", nullable = false)
    private CarVariant carVariant;


    private String engine_no;

    @Enumerated(EnumType.STRING)
    private CarStatus status;

    public Car() {
    }

    public Car(Long id, String registration_no, Set<Booking> bookings, CarVariant carVariant, String engine_no, CarStatus status) {
        this.id = id;
        this.registration_no = registration_no;
        this.bookings = bookings;
        this.carVariant = carVariant;
        this.engine_no = engine_no;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistration_no() {
        return registration_no;
    }

    public void setRegistration_no(String registration_no) {
        this.registration_no = registration_no;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public CarVariant getCarVariant() {
        return carVariant;
    }

    public void setCarVariant(CarVariant carVariant) {
        this.carVariant = carVariant;
    }

    public String getEngine_no() {
        return engine_no;
    }

    public void setEngine_no(String engine_no) {
        this.engine_no = engine_no;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }
}
