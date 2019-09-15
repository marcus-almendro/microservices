package dev.almendro.microservices.ordermanagement.domain.model;

import lombok.*;
import javax.persistence.*;

@Getter @NoArgsConstructor
@Entity @Table(name = "order_lines")
class OrderLine {
    @Id private int id;
    @Column private int productId;
    @Column private int quantity;
    @Column private double price;

    OrderLine(int id, int productId, int quantity, double price) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }
}
