package dev.almendro.microservices.productcatalog.interfaces.rest.dto;

import lombok.Data;

@Data
public class CatalogDTO {
    private int catalogId;
    private int currentItems;
    private double averagePrice;
    private double sumOfPrice;
    private int maxCategories;
}