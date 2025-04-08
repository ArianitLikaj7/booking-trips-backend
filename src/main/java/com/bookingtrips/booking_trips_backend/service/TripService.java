package com.bookingtrips.booking_trips_backend.service;

import com.bookingtrips.booking_trips_backend.dao.TripRepository;
import com.bookingtrips.booking_trips_backend.dto.TripDto;
import com.bookingtrips.booking_trips_backend.dto.request.TripRequest;
import com.bookingtrips.booking_trips_backend.dto.request.TripUpdateRequest;
import com.bookingtrips.booking_trips_backend.entity.Trip;
import com.bookingtrips.booking_trips_backend.exception.MismatchedInputException;
import com.bookingtrips.booking_trips_backend.exception.ResourceNotFoundException;
import com.bookingtrips.booking_trips_backend.mapper.TripMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class TripService {
    private final TripRepository tripRepository;
    private final TripMapper tripMapper;
    private final AuthenticationService authenticationService;
    private final UserService userService;

    public TripDto create(TripRequest request) {
        Trip trip = tripMapper.toEntity(request);
        trip.setCreatedBy(authenticationService.getLoggedInUser().getUserId());
        Trip tripInDb = tripRepository.save(trip);
        return tripMapper.toDto(tripInDb);
    }

    public TripDto getById(Long id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Trip with %s id not found", id)
                ));
        return tripMapper.toDto(trip);
    }

    public List<TripDto> getAll() {
        return tripRepository.findAll()
                .stream()
                .map(tripMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<TripDto> getAllMyTrips() {
      return tripRepository.findAllByCreatedBy(authenticationService.getLoggedInUser().getUserId());

    }

    public void saveEntity(Trip trip) {
        tripRepository.save(trip);
    }

    public Trip getEntityById(Long id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Trip with %s id not found", id)
                ));
    }

    public TripDto update(Long id, TripUpdateRequest request) {
        if (!id.equals(request.getId())) {
            throw new MismatchedInputException("Ids don't match");
        }
        Trip tripInDb = tripRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Trip with %s id not found", id))
        );
        tripMapper.toEntity(request, tripInDb);
        return tripMapper.toDto(tripRepository.save(tripInDb));
    }

    public void deleteById(Long id) {
        tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Trip with %s id not found", id)
                ));
        tripRepository.deleteById(id);
    }

    public List<TripDto> findTripsByProperties(String search) {
       List<TripDto> trips = tripRepository.findTripsByProperties(search);
        if (trips == null || trips.isEmpty()) {
            throw new ResourceNotFoundException(String.format("No trips found with search %s", search));
        }
        return trips;
    }

    public List<TripDto> findTripsByPrice(Double price) {
        return tripRepository.findTripsByPrice(price);
    }

    public Long findAvailableSeats(Long tripId) {
        Long availableSeats = tripRepository.findAvailableSeats(tripId);
        if (availableSeats == null) {
            throw new ResourceNotFoundException(String.format("No available seats found for trip with id %s", tripId));
        }
        return availableSeats;
    }
}
