package com.gusgd.ecommerce.dto;

import com.gusgd.ecommerce.entities.Product;

public class ProductDTO {
  private final Long id;
  private final String name;
  private final String description;
  private final Double price;
  private final String imgUrl;


  public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.imgUrl = imgUrl;
  }

  public ProductDTO(Product entity) {
    id = entity.getId();
    name = entity.getName();
    description = entity.getDescription();;
    price = entity.getPrice();
    imgUrl = entity.getImgUrl();
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Double getPrice() {
    return price;
  }

  public String getImgUrl() {
    return imgUrl;
  }
}