package com.roche.application.persistence.repository;

import com.roche.application.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * JPA repository for products.
 * <p>
 * Created on 15.04.20.
 *
 * @author waldemarkipka
 */
@Repository
public interface ProductJPARepository extends JpaRepository<ProductEntity, Long> {

    @Query("select e from ProductEntity e")
    List<ProductEntity> findAll();

    Optional<ProductEntity> findBySku(String sku);

}
