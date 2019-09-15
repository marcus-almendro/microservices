package dev.almendro.microservices.productcatalog.domain.model.catalog;

import dev.almendro.microservices.productcatalog.domain.model.category.CategoryId;
import dev.almendro.microservices.productcatalog.domain.model.common.DomainEvents;
import dev.almendro.microservices.productcatalog.domain.model.common.PriceSummary;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter @NoArgsConstructor
@Entity @Table
public class Catalog {
    @EmbeddedId private CatalogId catalogId;
    @Embedded private PriceSummary priceSummary;
    @Column private int maxCategories;

    public Catalog(CatalogId catalogId, int maxCategories){
        this();

        setCatalogId(catalogId);
        setPriceSummary(PriceSummary.Zero);
        setMaxCategories(maxCategories);
    }

    public void startCategoryCreation(CategoryId categoryId, int maxProducts, String categoryName){
        EnsureNumberOfCategoriesDoesNotExceedMaximumAllowed();
        setPriceSummary(priceSummary.addNewPrice(0));
        DomainEvents.publish(new CategoryCreationStarted(categoryId, categoryName, this.catalogId, maxProducts));
    }

    private void EnsureNumberOfCategoriesDoesNotExceedMaximumAllowed() {
        if (priceSummary.getCurrentItems() + 1 > maxCategories)
            throw new IllegalStateException("Maximum number of categories by design is " + maxCategories + ".");
    }

    private void setCatalogId(CatalogId catalogId) {
        this.catalogId = catalogId;
    }

    private void setPriceSummary(PriceSummary priceSummary) {
        this.priceSummary = priceSummary;
    }

    private void setMaxCategories(int maxCategories) {
        if (maxCategories <= 0)
            throw new IllegalArgumentException("maxCategories must be > 0");

        this.maxCategories = maxCategories;
    }

    public void updateCategoryPrice(PriceSummary oldPrice, PriceSummary newPrice) {
        setPriceSummary(priceSummary.replacePrice(
                oldPrice.getAveragePrice(),
                newPrice.getAveragePrice()));
    }
}