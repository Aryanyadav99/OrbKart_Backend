package com.eshop.Ecommerce.Service;

import com.eshop.Ecommerce.Payload.CartDTO;
import org.springframework.web.bind.annotation.PathVariable;

public interface CartService {
    public CartDTO addProductToCart(Long productId,Integer quantity);
}
