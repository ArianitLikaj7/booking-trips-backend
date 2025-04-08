package com.bookingtrips.booking_trips_backend.controller;

import com.bookingtrips.booking_trips_backend.dto.AuthenticationResponse;
import com.bookingtrips.booking_trips_backend.dto.CurrentLoggedInUserDto;
import com.bookingtrips.booking_trips_backend.dto.request.AuthenticationRequest;
import com.bookingtrips.booking_trips_backend.dto.request.RefreshTokenRequest;
import com.bookingtrips.booking_trips_backend.dto.request.RegisterRequest;
import com.bookingtrips.booking_trips_backend.service.AuthenticationService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody @NotNull AuthenticationRequest request){
        return new ResponseEntity<>(authenticationService.login(request), HttpStatus.OK);
    }

    @PostMapping("/register")
    public void register(@RequestBody @NotNull RegisterRequest request){
             authenticationService.register(request);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refreshToken(@RequestBody @NotNull RefreshTokenRequest refreshTokenRequest) {
        return new ResponseEntity<>(authenticationService.refreshToken(refreshTokenRequest), HttpStatus.OK);
    }

    @GetMapping("/auth-me")
    public CurrentLoggedInUserDto getLoggedInUser(){
        return authenticationService.getLoggedInUser();
    }
}
