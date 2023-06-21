package com.example.store52.application.product.service;

import com.example.store52.application.product.domain.model.Product;
import com.example.store52.application.product.domain.persistence.ProductRepository;
import com.example.store52.application.product.domain.service.ProductService;
import com.example.store52.shared.exception.ResourceNotFoundException;
import com.example.store52.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class ProductServiceImp implements ProductService {


    private static final String ENTITY="Product";
    private final ProductRepository productRepository;

    private final Validator validator;

    public ProductServiceImp(ProductRepository productRepository,Validator validator){
        this.productRepository=productRepository;
        this.validator=validator;
    }
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product getById(Long productId) {
        return productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException(ENTITY,productId));
    }

    @Override
    public Product create(Product product) {
        Set<ConstraintViolation<Product>> violations=validator.validate(product);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY,violations);

        //validator for name
        Product productWithName=productRepository.findProductByName(product.getName());
        if(productWithName!=null)
            throw new ResourceValidationException(ENTITY,"Product with the same name already exists");

        //validator for price
        if(product.getPrice()<=0)
            throw new ResourceValidationException(ENTITY,"A product can not cost less than 0");
        //Validator for quantity
        if(product.getQuantity()<=0||product.getQuantity()>369)
            throw new ResourceValidationException(ENTITY,"The product quantity is not supported");
        if(!("Delivery".equals(product.getTypeShipping())||("inHouse".equals(product.getTypeShipping()))))
            throw new ResourceValidationException(ENTITY,"Type Shipping should be Delivery or inHouse");

        return productRepository.save(product);
    }

    @Override
    public Product update(Long productId, Product request) {
        Set<ConstraintViolation<Product>> violations=validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);


        //validate if the product exist
        Optional<Product> existingProduct=productRepository.findById(request.getId());
        if(existingProduct.isEmpty())
            throw new ResourceNotFoundException(ENTITY,request.getId());

        //validate if the new name is used in other product
        Product productWithName = productRepository.findProductByName(request.getName());
        if (productWithName != null && !productWithName.getId().equals(productId)) {
            throw new ResourceValidationException(ENTITY, "Product with the same name already exists");
        }

        //validator for price
        if(request.getPrice()<=0)
            throw new ResourceValidationException(ENTITY,"A product can not cost less than 0");
        //Validator for quantity
        if(!(request.getQuantity()>0&&request.getQuantity()<369))
            throw new ResourceValidationException(ENTITY,"The product quantity is not supported");
        if(!("Delivery".equals(request.getTypeShipping())||("inHouse".equals(request.getTypeShipping()))))
            throw new ResourceValidationException(ENTITY,"Type Shipping should be Delivery or inHouse");



        Product updatedProduct = existingProduct.get()
                .withName(request.getName())
                .withPrice(request.getPrice())
                .withQuantity(request.getQuantity())
                .withTypeShipping(request.getTypeShipping());



        return productRepository.save(updatedProduct);
    }

    @Override
    public ResponseEntity<?> delete(Long productId) {
        return productRepository.findById(productId).map(product ->{
            productRepository.delete(product);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(ENTITY,productId));
    }
}
