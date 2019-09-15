package dev.almendro.microservices.productcatalog.interfaces.rest.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private long sku;
    private String name;
    private double basePrice;
    private double currentPrice;
}