package com.example.store52.application.admin.api;

import com.example.store52.application.admin.domain.service.AdminService;
import com.example.store52.application.admin.mapping.AdminMapper;
import com.example.store52.application.admin.resource.AdminResource;
import com.example.store52.application.admin.resource.CreateAdminResource;
import com.example.store52.application.admin.resource.UpdateAdminResource;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Validated
@RestController
@RequestMapping("api/v1/admin")
public class AdminController {
    public final AdminService adminService;
    private final AdminMapper mapper;

    public AdminController(AdminService adminService, AdminMapper mapper){
        this.adminService=adminService;
        this.mapper=mapper;

    }

    //GET ALL
    @GetMapping
    public List<AdminResource> getAllAdmins(){
        return mapper.modelList(adminService.getAll());
    }

    //GET BY ID
    @GetMapping("{adminId}")
    public AdminResource getAdminById(@PathVariable Long adminId){
        return mapper.toResource(adminService.getInfoAdminById(adminId));
    }

    //GET BY EMAIL AND PASSWORD
    @GetMapping("email/password/{adminEmail}/{adminPassword}")
    public AdminResource getInfoAgencyByEmailAndPassword(@PathVariable("adminEmail") String adminEmail, @PathVariable("adminPassword") String adminPassword) {
        return mapper.toResource(adminService.getByEmailAndPassword(adminEmail,adminPassword));
    }

    //POST
    @PostMapping
    public AdminResource createAdmin(@RequestBody CreateAdminResource resource){
        return mapper.toResource(adminService.create(mapper.toModel(resource)));
    }

    //UPDATE
    @PutMapping("/{adminId}")
    public AdminResource updateAdmin(@PathVariable Long adminId, @RequestBody UpdateAdminResource resource) {
        return mapper.toResource(adminService.update(adminId, mapper.toModel(resource)));
    }

    //DELETE
    @DeleteMapping("{adminId}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long adminId) {
        return adminService.delete(adminId);
    }




}
