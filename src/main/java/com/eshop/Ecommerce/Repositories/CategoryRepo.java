package com.eshop.Ecommerce.Repositories;

import com.eshop.Ecommerce.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
