package com.example.store52.application.product.api;


import com.example.store52.application.product.domain.service.ProductService;
import com.example.store52.application.product.mapping.ProductMapper;
import com.example.store52.application.product.resource.CreateProductResource;
import com.example.store52.application.product.resource.ProductResource;
import com.example.store52.application.product.resource.UpdateProductResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    public final ProductService productService;
    private final ProductMapper mapper;

    public ProductController(ProductService productService, ProductMapper mapper){
        this.productService=productService;
        this.mapper=mapper;

    }
    //GET ALL
    @GetMapping
    public Page<ProductResource> getAllAuthors(Pageable pageable){
        return mapper.modelListPage(productService.getAll(),pageable);
    }

    //GET BY ID
    @GetMapping("{productId}")
    public ProductResource getAuthorById(@PathVariable Long productId){
        return mapper.toResource(productService.getById(productId));
    }

    //POST
    @PostMapping
    public ProductResource createAuthor(@RequestBody CreateProductResource resource){
        return mapper.toResource(productService.create(mapper.toModel(resource)));
    }

    //UPDATE
    @PutMapping("/{productId}")
    public ProductResource updateAgency(@PathVariable Long authorId, @RequestBody UpdateProductResource resource) {
        return mapper.toResource(productService.update(authorId, mapper.toModel(resource)));
    }


    //DELETE
    @DeleteMapping("{productId}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long productId) {
        return productService.delete(productId);
    }




}
