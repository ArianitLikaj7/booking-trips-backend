package com.bookingtrips.booking_trips_backend.mapper;
import com.bookingtrips.booking_trips_backend.dto.TripDto;
import com.bookingtrips.booking_trips_backend.dto.request.TripRequest;
import com.bookingtrips.booking_trips_backend.dto.request.TripUpdateRequest;
import com.bookingtrips.booking_trips_backend.entity.Trip;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class TripMapper
        implements UpdateGenericMapper<Trip, TripDto, TripRequest, TripUpdateRequest>{

    private final ModelMapper mapper;
    @Override
    public TripDto toDto(Trip trip) {
        return mapper.map(trip, TripDto.class);
    }

    @Override
    public Trip toEntity(TripRequest request) {
        return mapper.map(request, Trip.class);
    }

    @Override
    public void toEntity(TripUpdateRequest updateRequest, Trip entity) {
       mapper.map(updateRequest,entity);
    }
}
