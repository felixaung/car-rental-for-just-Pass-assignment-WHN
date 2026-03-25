package com.car.rental.repositories;

import com.car.rental.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("""
        select p from Payment p
        left join fetch p.booking b
        left join fetch b.car c
        left join fetch c.carVariant
    """)
    List<Payment> findAllWithDetails();
}
