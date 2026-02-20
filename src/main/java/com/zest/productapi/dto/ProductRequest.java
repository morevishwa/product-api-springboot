package com.zest.productapi.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private String productName;
    private String createdBy;


    private List<ItemRequest> items;
}