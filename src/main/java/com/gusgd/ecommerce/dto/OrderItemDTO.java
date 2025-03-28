package com.gusgd.ecommerce.dto;

import com.gusgd.ecommerce.entities.OrderItem;

public class OrderItemDTO {
    private final  Long productId;
    private final String name;
    private final Double price;
    private final Integer quantity;
    private final String imgUrl;

    public OrderItemDTO(Long productId, String name, Double price, Integer quantity, String imgUrl) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imgUrl = imgUrl;
    }

    public OrderItemDTO(OrderItem entity) {
        productId = entity.getProduct().getId();
        name = entity.getProduct().getName();
        price = entity.getPrice();
        quantity = entity.getQuantity();
        imgUrl = entity.getProduct().getImgUrl();
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Double getSubTotal(){
        return (Double) (price * quantity);
    }

    public String getImgUrl(){
        return imgUrl;
    }
}
