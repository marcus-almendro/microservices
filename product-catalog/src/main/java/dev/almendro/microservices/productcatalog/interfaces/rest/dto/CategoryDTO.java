package dev.almendro.microservices.productcatalog.interfaces.rest.dto;

import lombok.Data;

@Data
public class CategoryDTO {
    private int categoryId;
    private String name;
    private int currentItems;
    private double averagePrice;
    private double sumOfPrice;
}