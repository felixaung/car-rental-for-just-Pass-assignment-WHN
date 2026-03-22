package com.car.rental.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "car_variant")
public class CarVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    private String model;

    private String year;

    @Column(nullable = true,name = "seat_capacity")
    private String seat;

    private String fuelType;

    private String color;

    private String image;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "price_per_day", precision = 12, scale = 2, nullable = false)
    private java.math.BigDecimal pricePerDay;

    @OneToMany(mappedBy = "carVariant")
    private Set<Car> car = new HashSet<>();


    public CarVariant() {
    }

    public CarVariant(Long id, String brand, String model, String year, String seat, String fuelType, String color, String image, String description, BigDecimal pricePerDay, Set<Car> car) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.seat = seat;
        this.fuelType = fuelType;
        this.color = color;
        this.image = image;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Set<Car> getCar() {
        return car;
    }

    public void setCar(Set<Car> car) {
        this.car = car;
    }
}
