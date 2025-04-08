    package com.bookingtrips.booking_trips_backend.dto.request;

import jakarta.validation.constraints.NotNull;

public record RefreshTokenRequest(@NotNull String token) {
}
