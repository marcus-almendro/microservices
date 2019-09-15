package dev.almendro.microservices.productcatalog.domain.model.product;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor @AllArgsConstructor @Getter
@Embeddable
public class SKU implements Serializable {
    @Column private long sku;
}
