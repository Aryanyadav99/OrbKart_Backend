package com.eshop.Ecommerce.Controller;

import com.eshop.Ecommerce.Payload.CartDTO;
import com.eshop.Ecommerce.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/carts/products/{productId}/quantity/{quantity}")
    public ResponseEntity<CartDTO> addProductToCart(@PathVariable Long productId,
                                                    @PathVariable  Integer quantity) {
        CartDTO cartDto=cartService.addProductToCart(productId,quantity);
        return new ResponseEntity<>(cartDto, HttpStatus.CREATED);
    }
    @GetMapping("/carts")
    public ResponseEntity<List<CartDTO>>getCarts(){
        List<CartDTO> carts=cartService.getCarts();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }
}
