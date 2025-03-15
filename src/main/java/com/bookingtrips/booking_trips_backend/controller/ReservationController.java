package com.bookingtrips.booking_trips_backend.controller;

import com.bookingtrips.booking_trips_backend.dto.ReservationDto;
import com.bookingtrips.booking_trips_backend.dto.ReservationDtoAndUserDto;
import com.bookingtrips.booking_trips_backend.dto.TripDto;
import com.bookingtrips.booking_trips_backend.dto.UserDto;
import com.bookingtrips.booking_trips_backend.dto.request.ReservationRequest;
import com.bookingtrips.booking_trips_backend.dto.request.ReservationUpdateRequest;
import com.bookingtrips.booking_trips_backend.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(@Valid @RequestBody ReservationRequest request) {
        ReservationDto reservationDto = reservationService.create(request);
        return new ResponseEntity<>(reservationDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable Long id) {
        ReservationDto reservationDto = reservationService.getById(id);
        return new ResponseEntity<>(reservationDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReservationDtoAndUserDto>> getAllReservations() {
        List<ReservationDtoAndUserDto> reservations = reservationService.getAll();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable Long id, @Valid @RequestBody ReservationUpdateRequest request) {
        ReservationDto updatedReservation = reservationService.update(id, request);
        return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/my-reservations")
    public ResponseEntity<List<TripDto>> myReservation() {
        List<TripDto> reservations = reservationService.myReservation();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/trip/{tripId}")
    public ResponseEntity<List<UserDto>> getByTripIdAndUserId(@PathVariable Long tripId) {
        List<UserDto> reservations = reservationService.getByTripIdAndUserId(tripId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countReservations() {
        Long count = reservationService.countReservation();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
