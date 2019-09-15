package dev.almendro.microservices.productcatalog.domain.model.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CatalogRepository extends JpaRepository<Catalog, CatalogId> {
    @Query(value = "SELECT ifnull(max(catalog_id),0) + 1 from catalog", nativeQuery = true)
    int nextCatalogId();
}

