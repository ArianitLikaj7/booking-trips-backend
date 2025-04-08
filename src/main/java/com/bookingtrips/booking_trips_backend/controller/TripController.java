package com.bookingtrips.booking_trips_backend.controller;

import com.bookingtrips.booking_trips_backend.dto.TripDto;
import com.bookingtrips.booking_trips_backend.dto.request.TripRequest;
import com.bookingtrips.booking_trips_backend.dto.request.TripUpdateRequest;
import com.bookingtrips.booking_trips_backend.service.TripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @PostMapping
    public ResponseEntity<TripDto> createTrip(@Valid @RequestBody TripRequest request) {
        TripDto tripDto = tripService.create(request);
        return new ResponseEntity<>(tripDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripDto> getTripById(@PathVariable Long id) {
        TripDto tripDto = tripService.getById(id);
        return new ResponseEntity<>(tripDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TripDto>> getAll() {
        List<TripDto> trips = tripService.getAll();
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @GetMapping("/my-trips")
    public ResponseEntity<List<TripDto>> getAllTrips() {
        List<TripDto> trips = tripService.getAllMyTrips();
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripDto> updateTrip(@PathVariable Long id, @Valid @RequestBody TripUpdateRequest request) {
        TripDto updatedTrip = tripService.update(id, request);
        return new ResponseEntity<>(updatedTrip, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        tripService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<TripDto>> findTripsByProperties(@RequestParam String search) {
        List<TripDto> trips = tripService.findTripsByProperties(search);
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @GetMapping("/price")
    public ResponseEntity<List<TripDto>> findTripsByPrice(@RequestParam Double price) {
        List<TripDto> trips = tripService.findTripsByPrice(price);
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @GetMapping("/{id}/available-seats")
    public ResponseEntity<Long> findAvailableSeats(@PathVariable Long id) {
        Long availableSeats = tripService.findAvailableSeats(id);
        return new ResponseEntity<>(availableSeats, HttpStatus.OK);
    }
}
