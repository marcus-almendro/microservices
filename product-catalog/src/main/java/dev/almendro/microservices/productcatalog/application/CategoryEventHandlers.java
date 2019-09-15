package dev.almendro.microservices.productcatalog.application;

import dev.almendro.microservices.productcatalog.domain.model.catalog.CategoryCreationStarted;
import dev.almendro.microservices.productcatalog.domain.model.category.Category;
import dev.almendro.microservices.productcatalog.domain.model.category.CategoryRepository;
import dev.almendro.microservices.productcatalog.domain.model.common.DomainEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CategoryEventHandlers {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostConstruct
    public void registerAll() {
        DomainEvents.subscribe(event -> {
            categoryRepository.save(new Category(
                    event.getId(),
                    event.getName(),
                    event.getCatalogId(),
                    event.getMaxProducts()));
        }, CategoryCreationStarted.class);
    }
}
