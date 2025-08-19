package com.eshop.Ecommerce.Repositories;

import com.eshop.Ecommerce.Model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
}
