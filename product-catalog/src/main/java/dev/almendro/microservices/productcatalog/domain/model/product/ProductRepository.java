package dev.almendro.microservices.productcatalog.domain.model.product;

import dev.almendro.microservices.productcatalog.domain.model.category.CategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, SKU> {
    List<Product> findByCategoryId(CategoryId categoryId);
}
