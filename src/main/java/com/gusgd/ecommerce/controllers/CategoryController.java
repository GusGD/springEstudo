package com.gusgd.ecommerce.controllers;

import com.gusgd.ecommerce.dto.CategoryDTO;
import com.gusgd.ecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

  @Autowired
  private CategoryService service;


  @GetMapping()
  public ResponseEntity <List<CategoryDTO>> findAll() {
    List <CategoryDTO> dto = service.findAll();
    return ResponseEntity.ok(dto);
  }
}