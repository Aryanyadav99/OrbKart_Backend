package com.eshop.Ecommerce.Service;

import com.eshop.Ecommerce.Payload.CartDTO;
import com.eshop.Ecommerce.Payload.CartItemDTO;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CartService {
    public CartDTO addProductToCart(Long productId,Integer quantity);

    List<CartDTO> getCarts();

    CartDTO getCartById(String emailId, Long cartId);
    @Transactional

    CartDTO updateProductQuantityInCart(Long productId, Integer quantity);

    String deleteProductFromCart(Long cartId, Long productId);

    void updateProductInCarts(Long cartId, Long productId);

    String createOrUpdateCartWithItems(List<CartItemDTO> cartItems);
}
