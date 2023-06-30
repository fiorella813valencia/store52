package com.example.store52.application.admin.mapping;

import org.springframework.context.annotation.Bean;

public class MappingConfiguration {
    @Bean
    public AdminMapper productMapper(){
        return new AdminMapper();
    }
}
