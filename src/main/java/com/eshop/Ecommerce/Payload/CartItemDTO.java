package com.eshop.Ecommerce.Payload;

import com.eshop.Ecommerce.Model.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private Long id;
    private CartItem cart;
    private ProductDTO product;
    private Integer quantity;
    private Double discount;
    private Double productPrice;
}