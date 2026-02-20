package com.zest.productapi.security.service;

import com.zest.productapi.security.dto.AuthResponse;
import com.zest.productapi.security.dto.LoginRequest;
import com.zest.productapi.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthResponse login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String accessToken = jwtUtils.generateToken(authentication.getName());
        String refreshToken = jwtUtils.generateRefreshToken(authentication.getName());

        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse refreshToken(String refreshToken) {

        if (!jwtUtils.validateJwtToken(refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }

        String username = jwtUtils.getUsernameFromToken(refreshToken);

        String newAccessToken = jwtUtils.generateToken(username);
        String newRefreshToken = jwtUtils.generateRefreshToken(username);

        return new AuthResponse(newAccessToken, newRefreshToken);
    }
}