package com.gusgd.ecommerce.repositories;

import com.gusgd.ecommerce.entities.Order;
import com.gusgd.ecommerce.entities.OrderItem;
import com.gusgd.ecommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
