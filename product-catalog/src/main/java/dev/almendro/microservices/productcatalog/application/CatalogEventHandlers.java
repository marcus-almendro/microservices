package dev.almendro.microservices.productcatalog.application;

import dev.almendro.microservices.productcatalog.domain.model.catalog.Catalog;
import dev.almendro.microservices.productcatalog.domain.model.catalog.CatalogRepository;
import dev.almendro.microservices.productcatalog.domain.model.category.CategoryPriceSummaryChanged;
import dev.almendro.microservices.productcatalog.domain.model.common.DomainEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CatalogEventHandlers {
    
    @Autowired
    private CatalogRepository catalogRepository;

    @PostConstruct
    public void registerAll() {
        DomainEvents.subscribe(event -> {
            Catalog catalog = catalogRepository.getOne(event.getCatalogId());
            catalog.updateCategoryPrice(event.getOldPrice(), event.getNewPrice());
            catalogRepository.save(catalog);
        }, CategoryPriceSummaryChanged.class);
    }
}
