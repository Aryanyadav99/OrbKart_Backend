package com.eshop.Ecommerce.Controller;

import com.eshop.Ecommerce.Model.Product;
import com.eshop.Ecommerce.Payload.ProductDTO;
import com.eshop.Ecommerce.Payload.ProductResponse;
import com.eshop.Ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO>addProduct(@RequestBody Product product,
                                                @PathVariable Long categoryId){
       ProductDTO productDTO= productService.addProduct(categoryId,product);
       return ResponseEntity.ok().body(productDTO);
    }

    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse> getAllProducts(){
        ProductResponse productResponse = productService.getAllProducts();
        return ResponseEntity.ok().body(productResponse);
    }
}
