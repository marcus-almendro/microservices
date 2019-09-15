package dev.almendro.microservices.ordermanagement.domain.model;

public enum OrderStatus {
    EMPTY, PENDING_ADDRESS, PENDING_PAYMENT, REVIEW, CLOSED
}
