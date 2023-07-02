package com.example.store52.application.product.domain.service;

import com.example.store52.application.product.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    //CRUD here
    List<Product> getAll();
    Page<Product> getAll(Pageable pageable);
    Product getById(Long productId);
    Product create(Product product);
    Product update(Long productId, Product request);

    ResponseEntity<?> delete(Long productId);

}
