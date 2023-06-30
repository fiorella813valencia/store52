package com.example.store52.application.admin.service;

import com.example.store52.application.product.domain.model.Product;
import com.example.store52.shared.exception.ResourceNotFoundException;
import com.example.store52.shared.exception.ResourceValidationException;
import com.example.store52.application.admin.domain.model.Admin;
import com.example.store52.application.admin.domain.persistence.AdminRepository;
import com.example.store52.application.admin.domain.service.AdminService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AdminServiceImp implements AdminService {
    private static final String ENTITY = "Agency";

    private final AdminRepository adminRepository;

    private final Validator validator;
    public AdminServiceImp(AdminRepository adminRepository, Validator validator) {
        this.adminRepository = adminRepository;
        this.validator = validator;
    }

    @Override
    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getByEmailAndPassword(String email, String password) {
        Admin admin = adminRepository.findByEmailAndPassword(email, password);
        if (admin == null) {
            throw new ResourceNotFoundException(ENTITY, Long.valueOf("Admin with email " + email + " and password not found"));
        }
        return admin;
    }

    @Override
    public Admin getByName(String name) {
        return adminRepository.findByName(name);
    }

    @Override
    public Admin create(Admin admin) {
        Set<ConstraintViolation<Admin>> violations=validator.validate(admin);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY,violations);

        //validator for name and last name
        Admin adminWithNameAndLastName=adminRepository.findByNameAndLastName(admin.getName(), admin.getLastName());
        if(adminWithNameAndLastName!=null)
            throw new ResourceValidationException(ENTITY,"Admin with the same name and last name already exists");

        //validator for email
        Admin adminWithEmail=adminRepository.findByEmail(admin.getEmail());
        if(adminWithEmail!=null)
            throw new ResourceValidationException(ENTITY,"Admin with the same email already exists");

        //validator for code
        Admin adminWithCode=adminRepository.findByCode(admin.getCode());
        if(adminWithCode!=null)
            throw new ResourceValidationException(ENTITY,"Admin with the code already exists");


        //validator for phone
        Admin adminWithPhoneNumber=adminRepository.findByPhoneNumber(admin.getPhoneNumber());
        if(adminWithPhoneNumber!=null)
            throw new ResourceValidationException(ENTITY,"Admin with the same phone already exists");

        return adminRepository.save(admin);
    }

    @Override
    public Admin update(Long adminId, Admin request) {

//        Set<ConstraintViolation<Admin>> violations=validator.validate(request);
//        if(!violations.isEmpty())
//            throw new ResourceValidationException(ENTITY, violations);

        //validate if the product exist
        Optional<Admin> existingAdmin=adminRepository.findById(request.getId());
        if(existingAdmin.isEmpty())
            throw new ResourceNotFoundException(ENTITY,request.getId());

//
//        //validate if the new name is used in other product
//        Admin adminWithNameAndLastName = adminRepository.findByNameAndLastName(request.getName(), request.getLastName());
//        if (adminWithNameAndLastName!=null) {
//            throw new ResourceValidationException(ENTITY, "Admin with the same name and last already exists");
//        }
//
//        //validator for code
//        Admin adminWithCode=adminRepository.findByCode(request.getCode());
//        if(adminWithCode!=null)
//            throw new ResourceValidationException(ENTITY,"Admin with the code already exists");



        Admin updatedAdmin = existingAdmin.get()
                .withName(request.getName())
                .withLastName(request.getLastName())
                .withEmail(request.getEmail())
                .withPassword(request.getPassword())
                .withPhoneNumber(request.getPhoneNumber())
                .withCode(request.getCode());



        return adminRepository.save(updatedAdmin);
    }

    @Override
    public Admin getInfoAdminById(Long adminId) {
        return adminRepository.findById(adminId).orElseThrow(()->new ResourceNotFoundException(ENTITY, adminId));
    }

    @Override
    public ResponseEntity<?> delete(Long adminId) {
        return adminRepository.findById(adminId).map(
                admin -> {
                    adminRepository.delete(admin);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, adminId));
    }
}
