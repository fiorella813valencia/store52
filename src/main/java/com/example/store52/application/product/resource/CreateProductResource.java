package com.example.store52.application.product.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductResource {

    //va lo que se va a poder manipular
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
