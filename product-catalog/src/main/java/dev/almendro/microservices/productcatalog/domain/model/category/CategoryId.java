package dev.almendro.microservices.productcatalog.domain.model.category;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor @NoArgsConstructor @Getter
@Embeddable
public class CategoryId implements Serializable {
    @Column private int categoryId;
}
