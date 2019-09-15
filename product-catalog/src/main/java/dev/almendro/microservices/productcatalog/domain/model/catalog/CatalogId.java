package dev.almendro.microservices.productcatalog.domain.model.catalog;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Getter @AllArgsConstructor @NoArgsConstructor
@Embeddable
public class CatalogId implements Serializable {
    @Column private int catalogId;
}
