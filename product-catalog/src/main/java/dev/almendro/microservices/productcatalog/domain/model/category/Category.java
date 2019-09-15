package dev.almendro.microservices.productcatalog.domain.model.category;

import dev.almendro.microservices.productcatalog.domain.model.catalog.CatalogId;
import dev.almendro.microservices.productcatalog.domain.model.common.DomainEvents;
import dev.almendro.microservices.productcatalog.domain.model.common.PriceSummary;
import dev.almendro.microservices.productcatalog.domain.model.product.Product;
import dev.almendro.microservices.productcatalog.domain.model.product.ProductAddedToCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter @NoArgsConstructor
@Entity @Table
public class Category {
    @EmbeddedId private CategoryId categoryId;
    @Column private String name;
    @Embedded private CatalogId catalogId;
    @Embedded private PriceSummary priceSummary;
    @Column private int maxProducts;

    public Category(CategoryId categoryId, String name, CatalogId catalogId, int maxProducts) {
        this();
        setCategoryId(categoryId);
        setName(name);
        setCatalogId(catalogId);
        setMaxProducts(maxProducts);
        setPriceSummary(PriceSummary.Zero);
    }

    public void linkProductToCategory(Product product) {
        EnsureNeitherCategoryHasMoreThanMaximumProductsAllowed();
        PriceSummary oldPrice = priceSummary;
        setPriceSummary(priceSummary.addNewPrice(product.getCurrentPrice()));
        DomainEvents.publish(new CategoryPriceSummaryChanged(catalogId, categoryId, oldPrice, priceSummary));
        DomainEvents.publish(new ProductAddedToCategory(product.getSku(), categoryId));
    }

    private void EnsureNeitherCategoryHasMoreThanMaximumProductsAllowed() {
        if (priceSummary.getCurrentItems() + 1 > maxProducts)
            throw new IllegalStateException("A categoryId must not have more than " + maxProducts + " products.");
    }

    private void setCategoryId(CategoryId categoryId) {
        this.categoryId = categoryId;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setMaxProducts(int maxProducts) {
        if (maxProducts <= 0)
            throw new IllegalArgumentException("maxProducts must be greater than 0");

        this.maxProducts = maxProducts;
    }

    private void setCatalogId(CatalogId catalogId) {
        this.catalogId = catalogId;
    }

    private void setPriceSummary(PriceSummary priceSummary) {
        this.priceSummary = priceSummary;
    }
}