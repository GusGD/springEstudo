package com.gusgd.ecommerce.services;

import com.gusgd.ecommerce.dto.ProductDTO;
import com.gusgd.ecommerce.entities.Product;
import com.gusgd.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

  @Autowired
  private ProductRepository repository;

  @Transactional(readOnly = true)
  public ProductDTO findById(Long id){
    Product product = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found. ID: " + id));
    return new ProductDTO(product);
  }

  @Transactional(readOnly = true)
  public Page<ProductDTO> findAll(Pageable pageable){
    Page<Product> result = repository.findAll(pageable);
    return result.map(ProductDTO::new);
  }

  @Transactional()
  public ProductDTO insert(ProductDTO dto){
    Product entity = new Product();
    copyDtoToEntity(dto,entity);
    return new ProductDTO(entity);
  }

  @Transactional()
  public ProductDTO update(Long id, ProductDTO dto){
      Product entity = repository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Product not found. ID: " + id));
      copyDtoToEntity(dto, entity);
      entity = repository.save(entity);
      return new ProductDTO(entity);

  }

  @Transactional(propagation = Propagation.SUPPORTS)
  public void delete(Long id) {
    Product entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found. ID: " + id));

    try {
      repository.delete(entity);
    } catch (DataIntegrityViolationException e) {
      throw new ResourceDataBaseException("Integrity violation. Cannot delete product with related entities.");
    }
  }

  private void copyDtoToEntity(ProductDTO dto, Product entity) {
    entity.setName(dto.getName());
    entity.setDescription(dto.getDescription());
    entity.setPrice(dto.getPrice());
    entity.setImgUrl(dto.getImgUrl());
  }
}
