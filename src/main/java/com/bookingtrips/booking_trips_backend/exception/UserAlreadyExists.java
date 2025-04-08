package com.bookingtrips.booking_trips_backend.exception;

public class UserAlreadyExists extends RuntimeException{
    public UserAlreadyExists(String message){
        super(message);
    }
}
