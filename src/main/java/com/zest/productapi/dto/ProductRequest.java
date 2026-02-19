package com.zest.productapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank(message = "Product name is mandatory")
    private String productName;

    @NotBlank(message = "Created by is mandatory")
    private String createdBy;
}
