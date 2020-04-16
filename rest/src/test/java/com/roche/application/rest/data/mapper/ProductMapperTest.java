package com.roche.application.rest.data.mapper;

import com.roche.application.persistence.entity.ProductEntity;
import com.roche.application.rest.data.request.Product;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * Tests ProductMapper.
 * <p>
 * Created on 16.04.20.
 *
 * @author waldemarkipka
 */
class ProductMapperTest {

    private ProductMapper sut = new ProductMapper();

    @Test
    void fromEntityAndToEntity() {
        ProductEntity entity = new ProductEntity(UUID.randomUUID().toString(), "Milk", 50);

        final Product product = sut.fromEntity(entity);
        assertNotNull(product);
        assertEquals(entity.getSku(), product.getSku());
        assertEquals(entity.getName(), product.getName());
        assertEquals(entity.getPrice(), product.getPrice());
        assertEquals(entity.getCreated(), product.getCreatedAt());

        final ProductEntity mappedEntity = sut.toEntity(product);
        assertNotNull(mappedEntity);
        assertEquals(product.getSku(), mappedEntity.getSku());
        assertEquals(product.getName(), mappedEntity.getName());
        assertEquals(product.getPrice(), mappedEntity.getPrice());
        assertEquals(product.getCreatedAt(), mappedEntity.getCreated());
    }

}