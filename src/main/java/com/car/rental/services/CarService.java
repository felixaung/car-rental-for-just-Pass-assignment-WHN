package com.car.rental.services;

import com.car.rental.models.Booking;
import com.car.rental.models.BookingStatus;
import com.car.rental.models.Car;
import com.car.rental.models.CarStatus;
import com.car.rental.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Car with id " + id + " not found"));
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public List<Car> findAllWithVariant() {
        return carRepository.findAllWithVariant();
    }

    public List<Car> findAllBookable(){
        return carRepository.findByStatus(CarStatus.Available);
    }

    public void changeCarStatus(long id, CarStatus status){
        Car car = carRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Car with id " + id + " not found"));
        car.setStatus(status);
        carRepository.save(car);
    }

    public void save(Car car){
        carRepository.save(car);
    }

    public void delete(Car car){
        carRepository.delete(car);
    }
}
