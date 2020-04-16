package com.roche.application.rest.data.mapper;

import com.roche.application.persistence.entity.ProductEntity;
import com.roche.application.rest.data.request.Product;

import java.util.Objects;

/**
 * Maps between product values.
 * <p>
 * Created on 16.04.20.
 *
 * @author waldemarkipka
 */
public class ProductMapper {

    public Product fromEntity(ProductEntity entity) {
        Objects.requireNonNull(entity);
        return new Product(entity.getSku(), entity.getName(), entity.getPrice(), entity.getCreated());
    }

    public ProductEntity toEntity(Product product) {
        Objects.requireNonNull(product);

        final ProductEntity entity = new ProductEntity(product.getSku(), product.getName(), product.getPrice());
        if (product.getCreatedAt() != null) {
            entity.setCreated(product.getCreatedAt());
        }
        return entity;
    }
}
