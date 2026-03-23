package com.car.rental.repositories;

import com.car.rental.models.Booking;
import com.car.rental.models.BookingStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("""
        select b from Booking b
        where b.car.id = :carId
          and b.status in :statuses
          and b.start_date <= :endDate
          and b.end_date >= :startDate
    """)
    List<Booking> findOverlappingBookings(
            Long carId,
            Instant startDate,
            Instant endDate,
            List<BookingStatus> statuses
    );

    @Query("""
    select b
    from Booking b
    join fetch b.car c
    join fetch c.carVariant
    join fetch b.customer
    where b.status = :status
""")
    List<Booking> findAllByBookingStatus(@Param("status") BookingStatus status);

    @EntityGraph(attributePaths = {"car", "payment", "customer"})
    List<Booking> findAllByCustomerEmailOrderByIdDesc(String email);
}
