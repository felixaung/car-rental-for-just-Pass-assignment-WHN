package com.car.rental.repositories;

import com.car.rental.models.CarVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarVariantRepository extends JpaRepository<CarVariant, Long> {
}
