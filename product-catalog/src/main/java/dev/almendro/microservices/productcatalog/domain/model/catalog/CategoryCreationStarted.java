package dev.almendro.microservices.productcatalog.domain.model.catalog;

import dev.almendro.microservices.productcatalog.domain.model.category.CategoryId;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreationStarted {
    private CategoryId id;
    private String name;
    private CatalogId catalogId;
    private int maxProducts;
}
