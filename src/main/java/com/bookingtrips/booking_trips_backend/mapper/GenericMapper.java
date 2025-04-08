package com.bookingtrips.booking_trips_backend.mapper;

public interface GenericMapper<E,D,R> {

    D toDto(E entity);
    E toEntity(R request);
}