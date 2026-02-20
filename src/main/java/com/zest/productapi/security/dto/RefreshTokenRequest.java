package com.zest.productapi.security.dto;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;
}