package dev.almendro.microservices.productcatalog.domain.model.category;

import dev.almendro.microservices.productcatalog.domain.model.common.PriceSummary;
import dev.almendro.microservices.productcatalog.domain.model.catalog.CatalogId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CategoryPriceSummaryChanged {
    private CatalogId catalogId;
    private CategoryId categoryId;
    private PriceSummary oldPrice;
    private PriceSummary newPrice;
}
