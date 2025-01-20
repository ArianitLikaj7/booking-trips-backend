package com.bookingtrips.booking_trips_backend.dto.request;

import com.bookingtrips.booking_trips_backend.entity.TypeOfTrip;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TripRequest {
    @NotNull(message = "Origin cannot be null")
    @Size(min = 1, max = 255, message = "Origin must be between 1 and 255 characters")
    private String origin;

    @NotNull(message = "Name cannot be null")
    private String companyName;

    @NotNull(message = "Destination cannot be null")
    @Size(min = 1, max = 255, message = "Destination must be between 1 and 255 characters")
    private String destination;


    private int availableSeats;

    @NotNull(message = "Total seats cannot be null")
    @Positive(message = "Total seats must be a positive number")
    private int totalSeats;

    @Size(min = 1, max = 255, message = "Route must be between 1 and 255 characters")
    private String route;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be a positive number")
    private Double price;

    @NotNull(message = "Title cannot be null")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    @Size(min = 1, max = 500, message = "Description must be between 1 and 500 characters")
    private String description;

    private String imageUrl;

    @NotNull(message = "Type of trip cannot be null")
    private TypeOfTrip typeOfTrip;

    private LocalDateTime localDateTime;
}
