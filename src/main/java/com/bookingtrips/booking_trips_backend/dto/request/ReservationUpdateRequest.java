package com.bookingtrips.booking_trips_backend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationUpdateRequest {
    @NotNull private Long id;
    @NotNull private Long tripId;
    private int  seatNumber;
}
