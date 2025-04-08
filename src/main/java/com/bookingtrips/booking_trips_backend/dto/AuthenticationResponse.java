package com.bookingtrips.booking_trips_backend.dto;


import com.bookingtrips.booking_trips_backend.entity.Role;

public record AuthenticationResponse(String token, String refreshToken, Role role) {
}
