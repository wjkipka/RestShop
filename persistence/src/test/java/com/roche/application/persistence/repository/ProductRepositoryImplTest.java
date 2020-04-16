package com.roche.application.persistence.repository;

import com.roche.application.persistence.entity.ProductEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests ProductRepository.
 * <p>
 * Created on 16.04.20.
 *
 * @author waldemarkipka
 */
public class ProductRepositoryImplTest extends PersistenceTestBase {

    @Autowired
    private ProductRepository sut;

    private ProductEntity[] savedEntities;

    @BeforeEach
    void setUp() {
        ProductEntity productButter = new ProductEntity(UUID.randomUUID().toString(), "Butter", 200);
        ProductEntity productBread = new ProductEntity(UUID.randomUUID().toString(), "Bread", 350);

        savedEntities = saveEntities(productButter, productBread);
    }

    @AfterEach
    void tearDown() {
        if (savedEntities != null) {
            deleteEntities(savedEntities);
        }
    }

    @Test
    @DisplayName("CRUD test for products")
    public void crud() {
        // case: create
        Arrays.stream(savedEntities).forEach(entity -> {
            assertNotNull(entity.getId());
            assertNotNull(entity.getCreated());
        });

        int count = sut.findAll().size();
        assertThat(count).isEqualTo(2);

        // case: update
        ProductEntity entityToChange = savedEntities[0];
        entityToChange.setPrice(1000L);
        saveEntities(entityToChange);
        final Optional<ProductEntity> changedEntityFromDb = sut.findBySku(entityToChange.getSku());
        assertNotNull(changedEntityFromDb);
        assertTrue(changedEntityFromDb.isPresent());
        assertEquals(1000, changedEntityFromDb.get().getPrice());


        // case: delete
        deleteEntities(savedEntities);

        count = sut.findAll().size();
        assertThat(count).isEqualTo(0);

        savedEntities = null;
    }

    @Test
    public void findBySku() {
        final Optional<ProductEntity> firstProductEntity = sut.findBySku(savedEntities[0].getSku());
        assertNotNull(firstProductEntity);
        assertTrue(firstProductEntity.isPresent());
    }

    private ProductEntity[] saveEntities(ProductEntity... entities) {
        return Arrays.stream(entities).map(sut::save).map(optional -> optional.orElse(null)).filter(Objects::nonNull).
                toArray(ProductEntity[]::new);
    }

    private void deleteEntities(ProductEntity... entities) {
        Arrays.stream(entities).forEach(sut::delete);
    }
}