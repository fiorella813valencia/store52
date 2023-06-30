package com.example.store52.application.admin.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class AdminResource {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private int phoneNumber;
    private String code;
}
