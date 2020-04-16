package com.roche.application.persistence.repository;

import com.roche.application.common.exception.DatabaseException;
import com.roche.application.common.exception.InputValidationException;
import com.roche.application.persistence.entity.ProductEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

/**
 * The implementation of the database repository handling the product entities.
 * <p>
 * Created on 15.04.20.
 *
 * @author waldemarkipka
 */
@Component
public class ProductRepositoryImpl implements ProductRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private ProductJPARepository repository;

    public ProductRepositoryImpl(ProductJPARepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductEntity> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error by searching for all products in the database!", e);
            throw new DatabaseException("Couldn't find all product entities!", e);
        }
    }

    @Override
    public Optional<ProductEntity> findBySku(String sku) {
        if (sku == null || sku.trim().isEmpty()) {
            throw new InputValidationException("The argument 'sku' is null!");
        }
        try {
            return repository.findBySku(sku);
        } catch (Exception e) {
            LOGGER.error("Error by searching for a product in the database with sku = {}!", sku, e);
            throw new DatabaseException("Couldn't find product entity by SKU!", e);
        }
    }

    @Override
    public Optional<ProductEntity> save(ProductEntity instance) {
        if (instance == null) {
            throw new InputValidationException("The product entity is null!");
        }
        try {
            return Optional.of(repository.save(instance));
        } catch (Exception e) {
            LOGGER.error("Error by storing the secret entity: " + instance, e);
            throw new DatabaseException("Couldn't save product entity!", e);
        }
    }

    @Override
    public void delete(ProductEntity entity) {
        if (entity == null) {
            throw new InputValidationException("The argument 'entity' is null!");
        }
        try {
            repository.delete(entity);
        } catch (Exception e) {
            throw new DatabaseException("Couldn't delete product entity!", e);
        }
    }
}
