package dev.almendro.microservices.productcatalog.application;

import dev.almendro.microservices.productcatalog.domain.model.common.DomainEvents;
import dev.almendro.microservices.productcatalog.domain.model.product.Product;
import dev.almendro.microservices.productcatalog.domain.model.product.ProductAddedToCategory;
import dev.almendro.microservices.productcatalog.domain.model.product.ProductRepository;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ProductEventHandlers {

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void registerAll() {
        DomainEvents.subscribe(event -> {
            Product product = productRepository.getOne(event.getSku());
            product.setCategoryId(event.getCategoryId());
            productRepository.save(product);
        }, ProductAddedToCategory.class);
    }
}
