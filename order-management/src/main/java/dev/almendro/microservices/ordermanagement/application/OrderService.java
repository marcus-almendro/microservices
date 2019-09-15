package dev.almendro.microservices.ordermanagement.application;

import dev.almendro.microservices.ordermanagement.domain.model.*;
import dev.almendro.microservices.ordermanagement.domain.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PriceService priceService;

    public List<Order> retrieveAllOrders() {
        return orderRepository.findAll();
    }

    public OrderId createOrder() {
        OrderId orderId = new OrderId(UUID.randomUUID());
        Order order = new Order(orderId);
        orderRepository.save(order);
        return orderId;
    }

    @Transactional
    public int addOrderLine(OrderId orderId, int productId, int quantity) {
        Order order = orderRepository.getOne(orderId);
        double updatedPrice = priceService.productPrice(productId);
        int orderLineId = order.addNewOrderLine(productId, quantity, updatedPrice);
        orderRepository.save(order);
        return orderLineId;
    }

    public void setDeliveryAddress(OrderId orderId, DeliveryAddress deliveryAddress) {
        Order order = orderRepository.getOne(orderId);
        order.setDeliveryAddress(deliveryAddress);
        orderRepository.save(order);
    }

    public void setPaymentDetails(OrderId orderId, PaymentDetails paymentDetails) {
        Order order = orderRepository.getOne(orderId);
        order.setPaymentDetails(paymentDetails);
        orderRepository.save(order);
    }
}
