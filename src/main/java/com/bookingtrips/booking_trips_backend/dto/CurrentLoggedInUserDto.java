package com.bookingtrips.booking_trips_backend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrentLoggedInUserDto {
        private Long userId;
        private String username;
        private String firstName;
        private String lastName;
        private String email;
        private String role;

}
