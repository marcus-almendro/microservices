package dev.almendro.microservices.productcatalog.domain.model.product;

import dev.almendro.microservices.productcatalog.domain.model.category.CategoryId;
import lombok.*;
import javax.persistence.*;

@Getter @NoArgsConstructor
@Entity @Table
public class Product {
    @EmbeddedId private SKU sku;
    @Column private String name;
    @Column private double basePrice;
    @Column private double currentPrice;
    @Embedded @Setter private CategoryId categoryId;

    public Product(SKU sku, String name, double basePrice, double currentPrice) {
        this.sku = sku;
        this.name = name;
        this.basePrice = basePrice;
        this.currentPrice = currentPrice;
    }
}