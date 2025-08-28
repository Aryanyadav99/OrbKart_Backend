package com.eshop.Ecommerce.Controller;

import com.eshop.Ecommerce.Configuration.AppConstants;
import com.eshop.Ecommerce.Model.Product;
import com.eshop.Ecommerce.Payload.ProductDTO;
import com.eshop.Ecommerce.Payload.ProductResponse;
import com.eshop.Ecommerce.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO>addProduct(@Valid  @RequestBody ProductDTO productDTO,
                                                @PathVariable Long categoryId){
       ProductDTO savedproductDTO= productService.addProduct(categoryId,productDTO);
       return ResponseEntity.ok().body(productDTO);
    }

    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse> getAllProducts(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "pageNumber",defaultValue = AppConstants.Page_Number ,required = false)Integer pageNumber,
            @RequestParam(name = "pageSize",defaultValue = AppConstants.Page_Size ,required = false) Integer pageSize,
            @RequestParam(name = "sortBy",defaultValue = AppConstants.Sort_ProductsBy ,required = false) String sortBy,
            @RequestParam(name = "sortOrder",defaultValue = AppConstants.Sort_Dir,required = false)  String sortOrder
    ){
        ProductResponse productResponse = productService.getAllProducts(pageNumber,pageSize,sortBy,sortOrder, keyword, category);
        return ResponseEntity.ok().body(productResponse);
    }

    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> getProductByCategory(@PathVariable  Long categoryId,
                                                                @RequestParam(name = "pageNumber",defaultValue = AppConstants.Page_Number ,required = false)Integer pageNumber,
                                                                @RequestParam(name = "pageSize",defaultValue = AppConstants.Page_Size ,required = false) Integer pageSize,
                                                                @RequestParam(name = "sortBy",defaultValue = AppConstants.Sort_ProductsBy ,required = false) String sortBy,
                                                                @RequestParam(name = "sortOrder",defaultValue = AppConstants.Sort_Dir,required = false)  String sortOrder){
        ProductResponse productResponse = productService.searchByCategory(categoryId, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductByCategory(@PathVariable  String keyword,
                                                                @RequestParam(name = "pageNumber",defaultValue = AppConstants.Page_Number ,required = false)Integer pageNumber,
                                                                @RequestParam(name = "pageSize",defaultValue = AppConstants.Page_Size ,required = false) Integer pageSize,
                                                                @RequestParam(name = "sortBy",defaultValue = AppConstants.Sort_ProductsBy ,required = false) String sortBy,
                                                                @RequestParam(name = "sortOrder",defaultValue = AppConstants.Sort_Dir,required = false)  String sortOrder){
        ProductResponse productResponse=productService.searchProductByKeyword(keyword,pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(productResponse, HttpStatus.FOUND);
    }

    @PutMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO,
                                                    @PathVariable Long productId){
        ProductDTO updatedProductDTO= productService.updateProduct(productId,productDTO);
        return new ResponseEntity<>(updatedProductDTO, HttpStatus.OK);

    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long productId){
        ProductDTO deletedproductDTO=productService.deleteProduct(productId);
        return new ResponseEntity<>(deletedproductDTO, HttpStatus.OK);
    }

    @PutMapping("/products/{productId}/image")
    public ResponseEntity<ProductDTO> updateProductImage(@PathVariable Long productId,
                                                         @RequestParam ("image") MultipartFile image) throws IOException {

        ProductDTO updatedProduct=productService.updateProductImage(productId,image);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
}
