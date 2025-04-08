package com.bookingtrips.booking_trips_backend.dto;

import com.bookingtrips.booking_trips_backend.entity.TypeOfTrip;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TripDto {
    private Long id;
    private String companyName;
    private Long createdBy;
    private String origin;
    private String destination;
    private int availableSeats;
    private int totalSeats;
    private String route;
    private Double price;
    private String title;
    private String description;
    private String imageUrl;
    private TypeOfTrip typeOfTrip;
    private LocalDateTime localDateTime;
}
