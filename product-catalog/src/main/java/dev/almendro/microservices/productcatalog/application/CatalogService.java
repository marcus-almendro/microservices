package dev.almendro.microservices.productcatalog.application;

import java.util.List;
import dev.almendro.microservices.productcatalog.ProductCatalogConfig;
import dev.almendro.microservices.productcatalog.domain.model.catalog.Catalog;
import dev.almendro.microservices.productcatalog.domain.model.catalog.CatalogId;
import dev.almendro.microservices.productcatalog.domain.model.catalog.CatalogRepository;
import dev.almendro.microservices.productcatalog.domain.model.category.CategoryId;
import dev.almendro.microservices.productcatalog.domain.model.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductCatalogConfig config;

    public List<Catalog> retrieveCatalogs(){
        return catalogRepository.findAll();
    }

    @Transactional
    public CatalogId createCatalog(){
        CatalogId catalogId = new CatalogId(catalogRepository.nextCatalogId());
        Catalog c = new Catalog(catalogId, config.getMaxCategories());
        catalogRepository.save(c);
        return c.getCatalogId();
    }

    @Transactional
    public CategoryId startCategoryCreation(int catalogId, String categoryName){
        Catalog catalog = catalogRepository.getOne(new CatalogId(catalogId));
        CategoryId categoryId = new CategoryId(categoryRepository.nextCategoryId());
        catalog.startCategoryCreation(categoryId, config.getMaxProducts(), categoryName);
        catalogRepository.save(catalog);
        return categoryId;
    }
}