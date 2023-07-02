package com.example.store52.application.product.mapping;


import org.springframework.context.annotation.Bean;

public class MappingConfiguration {

    @Bean
    public ProductMapper productMapper(){
        return new ProductMapper();
    }
}
