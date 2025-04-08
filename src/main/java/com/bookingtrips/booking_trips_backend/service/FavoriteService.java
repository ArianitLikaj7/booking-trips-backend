package com.bookingtrips.booking_trips_backend.service;

import com.bookingtrips.booking_trips_backend.dao.FavoriteRepository;
import com.bookingtrips.booking_trips_backend.dto.TripDto;
import com.bookingtrips.booking_trips_backend.entity.Favorite;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final AuthenticationService authenticationService;
    private final TripService tripService;

    @Transactional
    public Favorite addFavorite(Long tripId) {
        Long userId = authenticationService.getLoggedInUser().getUserId();
        if (favoriteRepository.existsByUserIdAndTripId(userId, tripId)) {
            throw new IllegalStateException("Trip is already in favorites");
        }
        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.setTripId(tripId);
        return favoriteRepository.save(favorite);
    }

    public List<TripDto> getUserFavorites() {
        Long userId = authenticationService.getLoggedInUser().getUserId();
        List<Favorite> favorites = favoriteRepository.findByUserId(userId);
        return favorites.stream()
                .map(favorite -> tripService.getById(favorite.getTripId()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void removeFavorite(Long tripId) {
        Long userId = authenticationService.getLoggedInUser().getUserId();
        favoriteRepository.deleteByUserIdAndTripId(userId, tripId);
    }
}
