package com.example.store52.application.product.mapping;

import com.example.store52.application.product.domain.model.Product;
import com.example.store52.application.product.resource.CreateProductResource;
import com.example.store52.application.product.resource.ProductResource;
import com.example.store52.application.product.resource.UpdateProductResource;
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
public class ProductMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public ProductResource toResource(Product model){
        return mapper.map(model, ProductResource.class);
    }

    public Page<ProductResource> modelListPage(List<Product> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,ProductResource.class),pageable,modelList.size());
    }


    public Product toModel(CreateProductResource resource){
        return mapper.map(resource,Product.class);

    }

    public Product toModel(UpdateProductResource resource){
        return mapper.map(resource,Product.class);
    }


}
