package dev.almendro.microservices.ordermanagement.domain.model;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter @AllArgsConstructor
@Embeddable
public class PaymentDetails {
    @Column private String cardNumber;
    @Column private String cardHolderName;
    @Column private int cvv;
    //etc
}
