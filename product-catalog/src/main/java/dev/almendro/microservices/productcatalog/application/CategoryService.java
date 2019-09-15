package dev.almendro.microservices.productcatalog.application;

import dev.almendro.microservices.productcatalog.ProductCatalogConfig;
import dev.almendro.microservices.productcatalog.domain.model.catalog.CatalogId;
import dev.almendro.microservices.productcatalog.domain.model.category.Category;
import dev.almendro.microservices.productcatalog.domain.model.category.CategoryId;
import dev.almendro.microservices.productcatalog.domain.model.category.CategoryRepository;
import dev.almendro.microservices.productcatalog.domain.model.product.Product;
import dev.almendro.microservices.productcatalog.domain.model.product.ProductRepository;
import dev.almendro.microservices.productcatalog.domain.model.product.SKU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCatalogConfig config;

    public List<Category> getCategoriesFromCatalog(int catalogId){
        return categoryRepository.findByCatalogId(new CatalogId(catalogId));
    }

    @Transactional
    public void linkProductToCategory(int categoryId, long sku){
        Product product = productRepository.getOne(new SKU(sku));
        Category category = categoryRepository.getOne(new CategoryId(categoryId));
        category.linkProductToCategory(product);
        categoryRepository.save(category);
    }
}