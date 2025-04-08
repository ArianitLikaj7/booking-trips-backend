package com.bookingtrips.booking_trips_backend.exception;

public class IlegalNumberOfSeatsException extends RuntimeException{
    public IlegalNumberOfSeatsException(String msg){
        super(msg);
    }
}
