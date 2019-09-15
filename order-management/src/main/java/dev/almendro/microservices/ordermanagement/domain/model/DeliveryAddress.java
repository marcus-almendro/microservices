package dev.almendro.microservices.ordermanagement.domain.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@AllArgsConstructor
@Embeddable
public class DeliveryAddress {
    @Column
    private String city;

    @Column
    private String address;
    //etc
}
