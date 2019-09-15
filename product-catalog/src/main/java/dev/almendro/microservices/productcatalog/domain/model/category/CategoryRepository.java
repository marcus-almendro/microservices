package dev.almendro.microservices.productcatalog.domain.model.category;

import dev.almendro.microservices.productcatalog.domain.model.catalog.CatalogId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, CategoryId> {
    @Query(value = "SELECT ifnull(max(category_id),0) + 1 from category", nativeQuery = true)
    int nextCategoryId();

    List<Category> findByCatalogId(CatalogId catalogId);
}

