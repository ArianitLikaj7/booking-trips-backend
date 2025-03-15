package com.bookingtrips.booking_trips_backend.service;

import com.bookingtrips.booking_trips_backend.dao.ReservationRepository;
import com.bookingtrips.booking_trips_backend.dto.ReservationDto;
import com.bookingtrips.booking_trips_backend.dto.ReservationDtoAndUserDto;
import com.bookingtrips.booking_trips_backend.dto.TripDto;
import com.bookingtrips.booking_trips_backend.dto.UserDto;
import com.bookingtrips.booking_trips_backend.dto.request.ReservationRequest;
import com.bookingtrips.booking_trips_backend.dto.request.ReservationUpdateRequest;
import com.bookingtrips.booking_trips_backend.entity.Reservation;
import com.bookingtrips.booking_trips_backend.entity.Trip;
import com.bookingtrips.booking_trips_backend.exception.IlegalNumberOfSeatsException;
import com.bookingtrips.booking_trips_backend.exception.MismatchedInputException;
import com.bookingtrips.booking_trips_backend.exception.ReservationAlreadyExists;
import com.bookingtrips.booking_trips_backend.exception.ResourceNotFoundException;
import com.bookingtrips.booking_trips_backend.mapper.ReservationMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final AuthenticationService authenticationService;
    private final TripService tripService;

    @Transactional
    public ReservationDto create(ReservationRequest request) {
        validateSeatAvailability(request.getTripId(), request.getSeatNumber());
        Reservation reservation = reservationMapper.toEntity(request);
        reservation.setUserId(authenticationService.getLoggedInUser().getUserId());
        mapTripToReservation(request, reservation);
        updateAvailableSeats(request.getTripId(), request.getSeatNumber());
        if (reservationRepository.existsByUserIdAndTripId(reservation.getUserId(), request.getTripId())) {
            throw new ReservationAlreadyExists(
                    "Reservation with user_id: " + reservation.getUserId() + " in this trip already exists");
        }
        Reservation reservationInDb = reservationRepository.save(reservation);
        return reservationMapper.toDto(reservationInDb);
    }

    public ReservationDto getById(Long id) {
        Reservation reservationInDb = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Reservation with %s id not found", id)));
        return reservationMapper.toDto(reservationInDb);
    }



    public List<ReservationDtoAndUserDto> getAll() {
        return reservationRepository.getReservationsByAdminId(authenticationService.getLoggedInUser().getUserId());
    }

    public List<TripDto> myReservation() {
        Long userId = authenticationService.getLoggedInUser().getUserId();
        List<TripDto> reservations = reservationRepository.findMyReservations(userId);
        if (reservations.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("No reservations found for user with id: %s", userId));
        }
        return reservations;
    }

    public List<UserDto> getByTripIdAndUserId(Long tripId) {
        Long userId = authenticationService.getLoggedInUser().getUserId();
        List<UserDto> reservations = reservationRepository.getReservationsByTripId(userId, tripId);
        if (reservations.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("No reservations found for user with id: %s and trip with id: %s", userId, tripId));
        }
        return reservations;
    }

    public Long countReservation() {
        Long userId = authenticationService.getLoggedInUser().getUserId();
        return reservationRepository.countReservation(userId);
    }

    public ReservationDto update(Long id, ReservationUpdateRequest request) {
        if (!id.equals(request.getId())) {
            throw new MismatchedInputException("Ids don't match");
        }
        Reservation reservationInDb = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Reservation with %s id not found", id)));
        reservationMapper.toEntity(request, reservationInDb);
        return reservationMapper.toDto(reservationRepository.save(reservationInDb));
    }

    public void delete(Long id) {
        Reservation reservationInDb = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Reservation with %s id not found", id)));
        reservationRepository.deleteById(id);
    }

    private void updateAvailableSeats(Long tripId, int seatNumber) {
        Trip trip = tripService.getEntityById(tripId);
        int availableSeats = trip.getAvailableSeats() - seatNumber;
        if (availableSeats < 0) {
            throw new IlegalNumberOfSeatsException("No available seats left.");
        }
        trip.setAvailableSeats(availableSeats);
        tripService.saveEntity(trip);
    }

    private void validateSeatAvailability(Long tripId, int seatNumber) {
        TripDto tripDto = tripService.getById(tripId);
        if (seatNumber > tripDto.getAvailableSeats() || seatNumber < 1) {
            throw new IlegalNumberOfSeatsException(
                    "Seat number: " + seatNumber + " is not available. Available seats: " + tripDto.getAvailableSeats());
        }
    }

    private void mapTripToReservation(ReservationRequest request, Reservation reservation) {
        TripDto tripDto = tripService.getById(request.getTripId());
        reservation.setTripId(tripDto.getId());
    }
}
