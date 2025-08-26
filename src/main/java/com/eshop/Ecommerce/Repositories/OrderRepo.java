package com.eshop.Ecommerce.Repositories;

import com.eshop.Ecommerce.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
