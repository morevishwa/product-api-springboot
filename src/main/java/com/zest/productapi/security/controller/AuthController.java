package com.zest.productapi.security.controller;

import com.zest.productapi.security.dto.AuthResponse;
import com.zest.productapi.security.dto.LoginRequest;
import com.zest.productapi.security.dto.RefreshTokenRequest;
import com.zest.productapi.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(
            @RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(
                authService.refreshToken(request.getRefreshToken())
        );
    }
}