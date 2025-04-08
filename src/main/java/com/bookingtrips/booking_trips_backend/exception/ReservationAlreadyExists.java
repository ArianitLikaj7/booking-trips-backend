package com.bookingtrips.booking_trips_backend.exception;

public class ReservationAlreadyExists extends RuntimeException{
    public ReservationAlreadyExists (String msg){
        super(msg);
    }
}
