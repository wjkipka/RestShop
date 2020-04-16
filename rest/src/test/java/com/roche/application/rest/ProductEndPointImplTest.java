package com.roche.application.rest;

import com.roche.application.persistence.entity.ProductEntity;
import com.roche.application.persistence.repository.ProductRepository;
import com.roche.application.rest.data.mapper.ProductMapper;
import com.roche.application.rest.data.request.Product;
import com.roche.application.rest.data.response.ProductsResponse;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests ProductEndPoint.
 * <p>
 * Created on 16.04.20.
 *
 * @author waldemarkipka
 */
class ProductEndPointImplTest {

    @Tested
    private ProductEndPointImpl sut;

    @Injectable
    private ProductRepository repository;

    @Mocked
    private UriInfo uriInfo;

    private ProductMapper mapper = new ProductMapper();

    private ProductEntity productButter;
    private ProductEntity productBread;

    @BeforeEach
    void setUp() {
        productButter = new ProductEntity(UUID.randomUUID().toString(), "Butter", 200);
        productBread = new ProductEntity(UUID.randomUUID().toString(), "Bread", 350);
    }

    @Test
    void getAllProducts() {
        List<ProductEntity> products = new ArrayList<>(2);
        products.add(productBread);
        products.add(productButter);
        new Expectations() {{
            repository.findAll();
            result = products;
        }};

        final Response response = sut.getAllProducts();
        assertSoftly(s -> {
            s.assertThat(response).isNotNull();
            s.assertThat(response.hasEntity()).isTrue();
            s.assertThat(response.getStatusInfo()).isSameAs(Response.Status.OK);
        });

        final ProductsResponse result = (ProductsResponse) response.getEntity();
        final List<Product> resultProducts = result.getProducts();
        assertSoftly(s -> {
            s.assertThat(resultProducts).isNotNull();
            s.assertThat(resultProducts.size()).isEqualTo(2);
        });
    }

    @Test
    void createProduct() {
        Product input = mapper.fromEntity(productBread);
        productBread.setId(1L);
        new Expectations(input) {{
            repository.save(mapper.toEntity(input));
            result = Optional.of(productBread);
        }};

        final Response response = sut.createProduct(input, uriInfo);
        assertSoftly(s -> {
            s.assertThat(response).isNotNull();
            s.assertThat(response.hasEntity()).isTrue();
            s.assertThat(response.getStatusInfo()).isSameAs(Response.Status.CREATED);
            final List<Object> locationHeaders = response.getHeaders().get("Location");
            s.assertThat(locationHeaders).isNotNull();
            s.assertThat(locationHeaders.size()).isEqualTo(1);
        });

        final Product result = (Product) response.getEntity();
        assertEquals(productBread.getSku(), result.getSku());
    }

    @Test
    void updateProduct() {
        Product input = mapper.fromEntity(productButter);
        productButter.setId(1L);
        new Expectations(input) {{
            repository.findBySku(input.getSku());
            result = Optional.of(productButter);
            repository.save(productButter);
            result = Optional.of(productButter);
        }};

        final Response response = sut.updateProduct(input);
        assertSoftly(s -> {
            s.assertThat(response).isNotNull();
            s.assertThat(response.hasEntity()).isTrue();
            s.assertThat(response.getStatusInfo()).isSameAs(Response.Status.OK);
        });


        final Product result = (Product) response.getEntity();
        assertEquals(productButter.getSku(), result.getSku());
    }

    @Test
    void deleteProduct() {
        Product input = mapper.fromEntity(productButter);
        productButter.setId(1L);
        new Expectations(input) {{
            repository.findBySku(input.getSku());
            result = Optional.of(productButter);
        }};

        final Response response = sut.deleteProduct(input);
        assertSoftly(s -> {
            s.assertThat(response).isNotNull();
            s.assertThat(response.hasEntity()).isFalse();
            s.assertThat(response.getStatusInfo()).isSameAs(Response.Status.OK);
        });
    }
}