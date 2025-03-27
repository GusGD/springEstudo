package com.gusgd.ecommerce.repositories;

import com.gusgd.ecommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {

}

