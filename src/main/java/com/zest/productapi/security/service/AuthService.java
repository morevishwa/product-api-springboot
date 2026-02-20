package com.zest.productapi.security.service;

import com.zest.productapi.security.dto.AuthResponse;
import com.zest.productapi.security.dto.LoginRequest;
import com.zest.productapi.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()
                        )
                );

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        String accessToken = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateToken(userDetails);

        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse refreshToken(String refreshToken) {

        // extract username
        String username = jwtService.extractUsername(refreshToken);

        // build minimal UserDetails (or load from DB if you want)
        UserDetails userDetails = org.springframework.security.core.userdetails
                .User.withUsername(username)
                .password("") // not required here
                .authorities("ROLE_USER")
                .build();

        if (!jwtService.isTokenValid(refreshToken, userDetails)) {
            throw new RuntimeException("Invalid refresh token");
        }

        String newAccessToken = jwtService.generateToken(userDetails);
        String newRefreshToken = jwtService.generateToken(userDetails);

        return new AuthResponse(newAccessToken, newRefreshToken);
    }
}