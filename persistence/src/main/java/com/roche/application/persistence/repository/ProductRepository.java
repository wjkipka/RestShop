package com.roche.application.persistence.repository;

import com.roche.application.persistence.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

/**
 * Interface for the database repository of product entities.
 * <p>
 * Created on 15.04.20.
 *
 * @author waldemarkipka
 */
public interface ProductRepository {

    List<ProductEntity> findAll();

    Optional<ProductEntity> findBySku(String sku);

    Optional<ProductEntity> save(ProductEntity instance);

    void delete(ProductEntity entity);
}
