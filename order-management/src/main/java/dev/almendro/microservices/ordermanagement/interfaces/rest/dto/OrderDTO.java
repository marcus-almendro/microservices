package dev.almendro.microservices.ordermanagement.interfaces.rest.dto;

import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDTO {
    private UUID id;
    private String status;
    private List<OrderLineDTO> orderLines;
    private DeliveryAddressDTO deliveryAddress;
    private PaymentDetailsDTO paymentDetails;
}
