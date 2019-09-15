package dev.almendro.microservices.productcatalog;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "catalog")
public class ProductCatalogConfig {
    private int maxCategories;
    private int maxProducts;
}
