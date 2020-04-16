package com.roche.application.persistence;

import com.roche.application.persistence.repository.ProductJPARepository;
import com.roche.application.persistence.repository.ProductRepository;
import com.roche.application.persistence.repository.ProductRepositoryImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Spring configuration for the persistence modules.
 * <p>
 * Created on 15.04.20.
 *
 * @author waldemarkipka
 */
@Configuration
@EnableJpaRepositories
@EntityScan("com.roche.application.persistence")
public class PersistenceConfig {

    @Bean
    @Primary
    public ProductRepository productRepository(ProductJPARepository productJPARepository) {
        return new ProductRepositoryImpl(productJPARepository);
    }
}
