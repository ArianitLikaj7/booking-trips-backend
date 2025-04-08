package com.bookingtrips.booking_trips_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDtoAndUserDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
    private Long tripId;
    private String companyName;
    private String origin;
    private String destination;
    private int availableSeats;
    private int totalSeats;
    private String route;
    private Double price;
    private String title;
    private String description;
    private String imageUrl;
}
