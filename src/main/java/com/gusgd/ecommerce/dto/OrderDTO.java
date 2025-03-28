package com.gusgd.ecommerce.dto;

import com.gusgd.ecommerce.entities.Order;
import com.gusgd.ecommerce.entities.OrderStatus;
import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    private final Long id;
    private final Instant moment;
    private final OrderStatus status;
    private final ClientDTO client;
    private final PaymentDTO payment;
    @NotEmpty(message = "Deve ter pelo menos um item")
    private final List<OrderItemDTO> items = new ArrayList<>();

    public OrderDTO(Long id, Instant moment, OrderStatus status, ClientDTO client, PaymentDTO payment) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
        this.payment = payment;
    }

    public OrderDTO(Order entity) {
        id      = entity.getId();
        moment  = entity.getMoment();
        status  = entity.getStatus();
        client  = new ClientDTO(entity.getClient());
        payment = (entity.getPayment() == null ) ? null : new PaymentDTO(entity.getPayment());
        entity.getItems().stream().map(OrderItemDTO::new).forEach(items::add);
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public ClientDTO getClient() {
        return client;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public Double getTotal(){
        double sum = 0.0;
        for (OrderItemDTO item: items){
            sum += item.getSubTotal();
        }
        return  sum;
    }
}
