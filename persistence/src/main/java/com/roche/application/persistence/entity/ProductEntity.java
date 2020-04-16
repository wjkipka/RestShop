package com.roche.application.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The product entity.
 * <p>
 * Created on 15.04.20.
 *
 * @author waldemarkipka
 */
@Entity
@Table(name = "T_PRODUCT")
public class ProductEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String sku;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private long price; // price in the currency multiplied by 100

    /**
     * Necessary for serialisation!
     */
    public ProductEntity() {
    }

    public ProductEntity(String sku, String name, long price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return super.toString() +
                " ProductEntity{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                "} " + super.toString();
    }
}
