package dev.almendro.microservices.ordermanagement.interfaces.rest.dto;

import lombok.Data;

@Data
public class PaymentDetailsDTO {
    private String cardNumber;
    private String cardHolderName;
    private int cvv;
    //etc
}
