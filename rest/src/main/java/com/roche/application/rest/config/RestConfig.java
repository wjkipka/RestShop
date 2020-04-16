package com.roche.application.rest.config;

import com.roche.application.persistence.repository.ProductRepository;
import com.roche.application.rest.ProductEndPoint;
import com.roche.application.rest.ProductEndPointImpl;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * REST configuration.
 * <p>
 * Created on 16.04.20.
 *
 * @author waldemarkipka
 */
@Configuration
@ComponentScan("com.roche.application")
public class RestConfig {

    @Bean
    public ResourceConfig resourceConfig() {
        ResourceConfig config = new ResourceConfig();
        config.register(OpenApiResource.class);
        return config;
    }

    @Bean
    @Primary
    public ProductEndPoint productEndPoint(ProductRepository productRepository) {
        ProductEndPoint endPoint = new ProductEndPointImpl(productRepository);
        resourceConfig().register(endPoint);
        return endPoint;
    }
}
