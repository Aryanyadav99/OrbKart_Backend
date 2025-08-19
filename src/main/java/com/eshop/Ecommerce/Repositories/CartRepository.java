package com.eshop.Ecommerce.Repositories;

import com.eshop.Ecommerce.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
