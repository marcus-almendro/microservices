package dev.almendro.microservices.ordermanagement.domain.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor @Getter
@Entity @Table(name = "orders")
public class Order {
    @EmbeddedId private OrderId orderId;
    @Column private OrderStatus status = OrderStatus.EMPTY;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderLine> orderLines = new ArrayList<>();

    @Embedded private DeliveryAddress deliveryAddress;
    @Embedded private PaymentDetails paymentDetails;

    public Order(OrderId orderId) {
        this.orderId = orderId;
    }

    public int addNewOrderLine(int productId, int quantity, double price) {
        int id = orderLines.size() + 1;
        orderLines.add(new OrderLine(id, productId, quantity, price));
        updateStatus();
        return id;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        updateStatus();
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
        updateStatus();
    }

    public void submitOrder() {
        if (status == OrderStatus.REVIEW) {
            status = OrderStatus.CLOSED;
        } else {
            throw new IllegalStateException("Submit only on review state");
        }
    }

    private void updateStatus() {
        if (orderLines.size() == 0) {
            status = OrderStatus.EMPTY;
            return;
        }

        if (deliveryAddress == null) {
            status = OrderStatus.PENDING_ADDRESS;
            return;
        }

        if (paymentDetails == null) {
            status = OrderStatus.PENDING_PAYMENT;
        }
        else status = OrderStatus.REVIEW;
    }
}
