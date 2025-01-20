package com.bookingtrips.booking_trips_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trips")
public class Trip extends BaseEntity{

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @Column(name = "available_seats")
    private int availableSeats;

    @Column(name = "total_seats")
    private int totalSeats;

    @Column(name = "route")
    private String route;

    @Column(name = "price")
    private Double price;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "local_date_time")
    private LocalDateTime localDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_trip")
    private TypeOfTrip typeOfTrip;

    @ManyToOne
    @JoinColumn(name = "created_by", insertable = false, updatable = false)
    @JsonBackReference
    private User user;
}
