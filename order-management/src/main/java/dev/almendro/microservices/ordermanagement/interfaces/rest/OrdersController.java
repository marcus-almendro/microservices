package dev.almendro.microservices.ordermanagement.interfaces.rest;

import dev.almendro.microservices.ordermanagement.application.OrderService;
import dev.almendro.microservices.ordermanagement.domain.model.Order;
import dev.almendro.microservices.ordermanagement.domain.model.OrderId;
import dev.almendro.microservices.ordermanagement.interfaces.rest.dto.OrderDTO;
import dev.almendro.microservices.ordermanagement.interfaces.rest.dto.OrderLineDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/orders")
    public List<OrderDTO> getAllOrders(){
        List<Order> orders = orderService.retrieveAllOrders();
        return Arrays.stream(orders.toArray(new Order[0]))
                .map(o -> modelMapper.map(o, OrderDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/orders")
    public ResponseEntity<Object> createOrder(){
        OrderId orderId = orderService.createOrder();
        return createdResponse(orderId.getId());
    }

    @PostMapping("/orders/{orderId}/orderLines")
    public ResponseEntity<Object> createOrderLine(@PathVariable UUID orderId, @RequestBody OrderLineDTO orderLineDTO) {
        int orderLineId = orderService.addOrderLine(new OrderId(orderId),
                orderLineDTO.getProductId(),
                orderLineDTO.getQuantity());
        return createdResponse(orderLineId);
    }

    private ResponseEntity<Object> createdResponse(Object obj){
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj)
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
