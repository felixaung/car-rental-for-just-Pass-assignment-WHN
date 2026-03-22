package com.car.rental.repositories;

import com.car.rental.models.BookingStatus;
import com.car.rental.models.Car;
import com.car.rental.models.CarStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("select c from Car c join fetch c.carVariant")
    List<Car> findAllWithVariant();

    @Query("""
    select distinct c
    from Car c
    join fetch c.carVariant
    left join fetch c.bookings b
    where b is null or b.status in :statuses
""")
    List<Car> findAllWithVariantAndBookingsByStatuses(@Param("statuses") Set<BookingStatus> statuses);

    @Query("""
    select distinct c
    from Car c
    join fetch c.carVariant
    left join fetch c.bookings
""")
    List<Car> findAllWithVariantAndBookings();

    @Query("select c from Car c join fetch c.carVariant where c.id = :id")
    Optional<Car> findByIdWithVariant(Long id);

    Optional<Car> findByIdAndStatus(Long id, BookingStatus status);
    List<Car> findByStatus(CarStatus status);
}
