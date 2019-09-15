package dev.almendro.microservices.productcatalog.domain.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import javax.persistence.*;

@AllArgsConstructor @Getter
@Embeddable
public class PriceSummary {
    public static PriceSummary Zero = new PriceSummary(0, 0, 0);

    @Column private int currentItems;
    @Column private double averagePrice;
    @Column private double sumOfPrice;

    public PriceSummary addNewPrice(double price) {
        return new PriceSummary(currentItems + 1,
                (sumOfPrice + price) / (currentItems + 1),
                sumOfPrice + price);
    }

    public PriceSummary replacePrice(double oldPrice, double newPrice) {
        return new PriceSummary(currentItems,
                (sumOfPrice - oldPrice + newPrice) / currentItems,
                sumOfPrice - oldPrice + newPrice);
    }

    private PriceSummary() {
    }
}
