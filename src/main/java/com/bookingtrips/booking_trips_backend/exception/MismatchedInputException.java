package com.bookingtrips.booking_trips_backend.exception;

public class MismatchedInputException extends RuntimeException{
    public MismatchedInputException(String message){
        super(message);
    }
}
