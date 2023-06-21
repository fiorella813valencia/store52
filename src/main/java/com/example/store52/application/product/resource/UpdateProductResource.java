package com.example.store52.application.product.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductResource {

    private Long Id;
    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private String description;

    @NotNull
    private Double price;

    @NotNull
    private Integer quantity;
    @NotBlank
    @NotNull
    private String typeShipping;
}
