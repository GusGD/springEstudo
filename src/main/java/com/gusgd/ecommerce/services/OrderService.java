package com.gusgd.ecommerce.services;

import com.gusgd.ecommerce.dto.OrderDTO;
import com.gusgd.ecommerce.entities.Order;
import com.gusgd.ecommerce.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id){
        Order order = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found. ID: " + id));
        return new OrderDTO(order);
    }
}
