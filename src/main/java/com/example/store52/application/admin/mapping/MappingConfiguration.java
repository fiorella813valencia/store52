package com.example.store52.application.admin.mapping;

import com.example.store52.application.product.mapping.ProductMapper;
import org.springframework.context.annotation.Bean;

public class MappingConfiguration {
    @Bean
    public ProductMapper productMapper(){
        return new ProductMapper();
    }
}
