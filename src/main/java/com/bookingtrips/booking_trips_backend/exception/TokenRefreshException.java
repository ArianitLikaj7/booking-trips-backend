package com.bookingtrips.booking_trips_backend.exception;

public class TokenRefreshException extends RuntimeException{

    public TokenRefreshException(String message) {
        super(message);
    }
}
