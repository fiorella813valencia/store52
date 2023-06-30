package com.example.store52.application.admin.mapping;

import com.example.store52.application.admin.domain.model.Admin;
import com.example.store52.application.admin.resource.AdminResource;
import com.example.store52.application.admin.resource.CreateAdminResource;
import com.example.store52.application.admin.resource.UpdateAdminResource;
import com.example.store52.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
@Component
@EnableAutoConfiguration
public class AdminMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public AdminResource toResource(Admin model) { return mapper.map(model, AdminResource.class); }

    public Admin toModel(CreateAdminResource resource) { return mapper.map(resource, Admin.class); }

    public Admin toModel(UpdateAdminResource resource) { return mapper.map(resource, Admin.class); }

    public Page<AdminResource> modelListPage(List<Admin> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, AdminResource.class), pageable, modelList.size());
    }


    public List<AdminResource> modelList(List<Admin> modelList) {
        return mapper.mapList(modelList, AdminResource.class);
    }
}
