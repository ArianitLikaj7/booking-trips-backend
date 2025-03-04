package com.bookingtrips.booking_trips_backend.dao;

import com.bookingtrips.booking_trips_backend.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    boolean existsByUserIdAndTripId(Long userId, Long tripId);
    List<Favorite> findByUserId(Long userId);
    void deleteByUserIdAndTripId(Long userId, Long tripId);
}
