package com.bookingtrips.booking_trips_backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponseDto {

    private String errorCode;
    private String message;
    private Map<String, String> details;
}