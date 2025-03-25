package com.gusgd.ecommerce.controllers;

import com.gusgd.ecommerce.dto.OrderDTO;
import com.gusgd.ecommerce.dto.ProductDTO;
import com.gusgd.ecommerce.services.OrderService;
import com.gusgd.ecommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

  @Autowired
  private OrderService service;

  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  @GetMapping(value ="/{id}")
  public ResponseEntity <OrderDTO> findById(@PathVariable Long id) {
    OrderDTO dto = service.findById(id);
    return ResponseEntity.ok(dto);
  }
}