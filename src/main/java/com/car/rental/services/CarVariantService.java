package com.car.rental.services;

import com.car.rental.models.CarVariant;
import com.car.rental.repositories.CarVariantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarVariantService {

    private final CarVariantRepository carVariantRepository;

    public CarVariantService(CarVariantRepository carVariantRepository) {
        this.carVariantRepository = carVariantRepository;
    }

    public List<CarVariant> getAll() {
        return carVariantRepository.findAll();
    }
    public CarVariant getById(Long id) {
        return carVariantRepository.findById(id).orElseThrow(()-> new IllegalArgumentException());
    }

    public void saveCarVariant(CarVariant carVariant) {
        carVariantRepository.save(carVariant);
    }

    public void deleteCarVariantById(Long id) {
        carVariantRepository.deleteById(id);
    }




}
