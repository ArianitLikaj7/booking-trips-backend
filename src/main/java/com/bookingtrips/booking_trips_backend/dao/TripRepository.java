package com.bookingtrips.booking_trips_backend.dao;

import com.bookingtrips.booking_trips_backend.dto.TripDto;
import com.bookingtrips.booking_trips_backend.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {

    @Query("SELECT new com.bookingtrips.booking_trips_backend.dto.TripDto(" +
            "t.id, t.companyName, t.createdBy, t.origin, t.destination, t.availableSeats, " +
            "t.totalSeats, t.route, t.price, t.title, t.description, t.imageUrl, t.typeOfTrip,t.localDateTime) " +
            "FROM Trip t WHERE t.createdBy = :userId")
    List<TripDto> findAllByCreatedBy(Long userId);

    @Query("SELECT new com.bookingtrips.booking_trips_backend.dto.TripDto(" +
            "t.id, t.companyName, t.createdBy, t.origin, t.destination, t.availableSeats, " +
            "t.totalSeats, t.route, t.price, t.title, t.description, t.imageUrl, t.typeOfTrip, t.localDateTime) " +
            "FROM Trip t WHERE t.origin LIKE %:search% OR t.destination LIKE %:search% OR t.route LIKE %:search%")
    List<TripDto> findTripsByProperties(String search);

    @Query("SELECT new com.bookingtrips.booking_trips_backend.dto.TripDto(" +
            "t.id, t.companyName, t.createdBy, t.origin, t.destination, t.availableSeats, " +
            "t.totalSeats, t.route, t.price, t.title, t.description, t.imageUrl, t.typeOfTrip, t.localDateTime) " +
            "FROM Trip t WHERE t.price = :price")
    List<TripDto> findTripsByPrice(Double price);

    @Query("SELECT t.availableSeats FROM Trip t WHERE t.id = :tripId AND t.availableSeats > 0")
    Long findAvailableSeats(Long tripId);
}
