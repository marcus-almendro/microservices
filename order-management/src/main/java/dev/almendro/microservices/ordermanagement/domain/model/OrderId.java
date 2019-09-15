package dev.almendro.microservices.ordermanagement.domain.model;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Getter @AllArgsConstructor @NoArgsConstructor
@Embeddable
public class OrderId implements Serializable {
    @Column private UUID id;
}
