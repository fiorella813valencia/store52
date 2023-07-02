package com.example.store52.application.product.domain.persistence;

import com.example.store52.application.product.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAll();
    List<Product> findProductsByName(String name);

    Product findProductByName(String name);
    Product findByPrice(double price);
    Product findByQuantity(int quantity);
    Product findByTypeShipping(String typeShipping);



}
