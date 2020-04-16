package com.roche.application.rest.data.response;

import com.roche.application.rest.data.request.Product;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 15.04.20.
 *
 * @author waldemarkipka
 */
public class ProductsResponse {

    @Schema(description = "The products data")
    @Valid
    private List<Product> products = new ArrayList<>();

    /**
     * Used for JSON serialisation!
     */
    public ProductsResponse() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        if (product != null) {
            products.add(product);
        }
    }
}
