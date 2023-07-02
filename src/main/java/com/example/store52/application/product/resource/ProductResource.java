package com.example.store52.application.product.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ProductResource {

    private Long Id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String typeShipping;
}
