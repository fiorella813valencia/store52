package com.example.store52.application.admin.domain.service;

import com.example.store52.application.admin.domain.model.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {

    List<Admin> getAll();

//    Page<Admin> getAll(Pageable pageable);

    //GET BY EMAIL AND PASSWORD
    Admin getByEmailAndPassword(String email,String password);

    //GET BY NAME
    Admin getByName(String name);

    // Create a new agency
    Admin create(Admin admin);

    // Update agency
    Admin update(Long adminId, Admin admin);

    // Get info agency by id
    Admin getInfoAdminById(Long adminId);

    ResponseEntity<?> delete(Long adminId);
}
