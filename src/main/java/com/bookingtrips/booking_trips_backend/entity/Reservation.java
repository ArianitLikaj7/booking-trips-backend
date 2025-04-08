package com.bookingtrips.booking_trips_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation extends BaseEntity{

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "seat_number")
    private int seatNumber;

    @Column(name = "trip_id")
    private Long tripId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "trip_id", insertable = false, updatable = false)
    @JsonBackReference
    private Trip trip;

}
