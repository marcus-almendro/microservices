package dev.almendro.microservices.productcatalog.application;

import dev.almendro.microservices.productcatalog.domain.model.category.CategoryId;
import dev.almendro.microservices.productcatalog.domain.model.product.Product;
import dev.almendro.microservices.productcatalog.domain.model.product.ProductRepository;
import dev.almendro.microservices.productcatalog.domain.model.product.SKU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> retrieveProducts() {
        return productRepository.findAll();
    }

    public Product retrieveProduct(long sku) {
        return productRepository.getOne(new SKU(sku));
    }

    public void createProduct(long sku, String name, double basePrice, double currentPrice) {
        Product product = new Product(new SKU(sku), name, basePrice, currentPrice);
        productRepository.save(product);
    }

    public List<Product> retrieveCategoryProducts(int categoryId){
        return productRepository.findByCategoryId(new CategoryId(categoryId));
    }

}