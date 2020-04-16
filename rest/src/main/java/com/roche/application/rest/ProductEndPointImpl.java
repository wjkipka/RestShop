package com.roche.application.rest;

import com.roche.application.persistence.entity.ProductEntity;
import com.roche.application.persistence.repository.ProductRepository;
import com.roche.application.rest.data.mapper.ProductMapper;
import com.roche.application.rest.data.request.Product;
import com.roche.application.rest.data.response.ProductsResponse;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of the REST end point interface for the product data.
 * <p>
 * Created on 15.04.20.
 *
 * @author waldemarkipka
 */
@Service
public class ProductEndPointImpl implements ProductEndPoint {

    private ProductRepository repository;

    private ProductMapper mapper = new ProductMapper();

    public ProductEndPointImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Response getAllProducts() {
        ProductsResponse result = new ProductsResponse();
        repository.findAll().forEach(entity -> result.addProduct(mapper.fromEntity(entity)));
        return Response.ok(result).build();
    }

    @Override
    public Response createProduct(Product product, UriInfo uriInfo) {
        Objects.requireNonNull(product);

        final Optional<ProductEntity> entityOptional = repository.save(mapper.toEntity(product));
        if (entityOptional.isPresent()) {
            final ProductEntity entity = entityOptional.get();
            return Response.created(buildUri(entity, uriInfo)).entity(mapper.fromEntity(entity)).build();
        } else {
            throw new ProcessingException("Couldn't store new product in the database!");
        }
    }

    @Override
    public Response updateProduct(@Valid @NotNull Product product) {
        Objects.requireNonNull(product);

        final Optional<ProductEntity> optionalEntity = repository.findBySku(product.getSku());
        if (optionalEntity.isPresent()) {
            final ProductEntity entityInDb = optionalEntity.get();
            final ProductEntity entityToSave = mapper.toEntity(product);
            entityToSave.setId(entityInDb.getId());
            entityToSave.setVersion(entityInDb.getVersion());
            entityToSave.setCreated(entityInDb.getCreated());

            final Optional<ProductEntity> savedEntity = repository.save(entityToSave);
            return savedEntity.map(entity -> Response.ok(mapper.fromEntity(entity)).build()).
                    orElseThrow(() -> new ProcessingException("Couldn't update a product!"));
        } else {
            throw new ProcessingException("Couldn't find an existing product in the database!");
        }
    }

    @Override
    public Response deleteProduct(@Valid @NotNull Product product) {
        Objects.requireNonNull(product);

        final Optional<ProductEntity> optionalEntity = repository.findBySku(product.getSku());
        if (optionalEntity.isPresent()) {
            repository.delete(optionalEntity.get());
            return Response.ok().build();
        } else {
            throw new ProcessingException("Couldn't find an existing product in the database!");
        }
    }

    private URI buildUri(ProductEntity productEntity, UriInfo uriInfo) {
        return uriInfo.getAbsolutePathBuilder().path(productEntity.getSku()).build();
    }
}
