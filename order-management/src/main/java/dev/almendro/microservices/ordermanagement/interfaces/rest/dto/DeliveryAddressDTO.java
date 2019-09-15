package dev.almendro.microservices.ordermanagement.interfaces.rest.dto;

import lombok.Data;

@Data
public class DeliveryAddressDTO {
    private String city;
    private String address;
    //etc
}
