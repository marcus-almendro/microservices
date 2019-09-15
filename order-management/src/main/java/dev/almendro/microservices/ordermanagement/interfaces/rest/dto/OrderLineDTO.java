package dev.almendro.microservices.ordermanagement.interfaces.rest.dto;

import lombok.Data;

@Data
public class OrderLineDTO {
    private int id;
    private int productId;
    private int quantity;
    private double price;
}
