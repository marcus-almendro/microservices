package dev.almendro.microservices.productcatalog.domain.model.product;

import dev.almendro.microservices.productcatalog.domain.model.category.CategoryId;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductAddedToCategory {
    private SKU sku;
    private CategoryId categoryId;
}
