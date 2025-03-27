package com.gusgd.ecommerce.services;

import com.gusgd.ecommerce.dto.OrderDTO;
import com.gusgd.ecommerce.dto.OrderItemDTO;
import com.gusgd.ecommerce.dto.ProductDTO;
import com.gusgd.ecommerce.entities.*;
import com.gusgd.ecommerce.repositories.OrderItemRepository;
import com.gusgd.ecommerce.repositories.OrderRepository;
import com.gusgd.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id){
        Order order = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found. ID: " + id));
        authService.validateSelfOrAdmin(order.getClient().getId());
        return new OrderDTO(order);
    }

    @Transactional()
    public OrderDTO insert(OrderDTO dto){
        Order order = new Order();
        User user = userService.authenticated();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        order.setClient(user);

        for(OrderItemDTO itemDto : dto.getItems()){
            Product product = productRepository.getReferenceById(itemDto.getProductId());
            OrderItem item = new OrderItem(order,product, product.getPrice(), itemDto.getQuantity());
            order.getItems().add(item);
        }
        repository.save(order);
        orderItemRepository.saveAll(order.getItems());
        return new OrderDTO(order);
    }
}
