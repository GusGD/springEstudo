package com.gusgd.ecommerce.dto;

import com.gusgd.ecommerce.entities.Category;
import com.gusgd.ecommerce.entities.Product;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

public class ProductDTO {
  private final Long id;
  @NotBlank(message = "Campo é de preenchimento obrigatório.")
  @Size(min = 3, message = "Campo precisa de 3 ou mais caracteres.")
  private final String name;
  @NotBlank(message = "Campo é de preenchimento obrigatório.")
  @Size(min = 10, message = "Campo precisa de 10 ou mais caracteres.")
  private final String description;
  @NotNull(message = "Campo requerido")
  @Positive(message = "O Preço deve ser positivo")
  private final Double price;
  private final String imgUrl;

  @NotEmpty(message = "Deve haver ao menos uma categoria")
  private final List<CategoryDTO> categories = new ArrayList<>();

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
    description = entity.getDescription();
    price = entity.getPrice();
    imgUrl = entity.getImgUrl();
    for (Category cat : entity.getCategories()) {
      categories.add(new CategoryDTO(cat));
    }
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

  public List<CategoryDTO> getCategories() {
    return categories;
  }
}
