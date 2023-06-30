package com.example.store52.application.admin.domain.persistence;

import com.example.store52.application.admin.domain.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin,Long> {

    List<Admin> findAll();
    Admin findByEmail(String email);
    Admin findByPhoneNumber(int phoneNumber);
    Admin findByName(String name);
    Admin findByNameAndLastName(String name, String lastName);
    Admin findByCode(String code);
    Admin findByEmailAndPassword(String email, String password);
    Admin findByPassword(String password);
}
