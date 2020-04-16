package com.roche.application.rest.data.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created on 15.04.20.
 *
 * @author waldemarkipka
 */
public class Product {

    @Schema(description = "The unique SKU")
    @Size(min = 5, max = 30)
    @NotBlank(message = "The SKU is empty!")
    @NotNull
    private String sku;  // unique

    @Schema(description = "The product name")
    @Size(max = 40)
    @NotBlank(message = "The product name is empty!")
    @NotNull
    private String name;

    @Schema(description = "The price of the product in currency multiplied by 100")
    @NotNull
    private long price; // price in the currency multiplied by 100

    @Schema(description = "The creation date of the product")
    private LocalDateTime createdAt;

    /**
     * Used for JSON serialisation!
     */
    public Product() {
    }

    public Product(String sku, String name, long price, LocalDateTime createdAt) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.createdAt = createdAt;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(getSku(), product.getSku());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getSku());
    }

    @Override
    public String toString() {
        return "Product{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", createdAt=" + createdAt +
                '}';
    }
}
