package com.eshop.Ecommerce.Service.ServiceImpl;

import com.eshop.Ecommerce.Exception.ResourceNotFoundException;
import com.eshop.Ecommerce.Model.Category;
import com.eshop.Ecommerce.Model.Product;
import com.eshop.Ecommerce.Payload.ProductDTO;
import com.eshop.Ecommerce.Payload.ProductResponse;
import com.eshop.Ecommerce.Repositories.CategoryRepo;
import com.eshop.Ecommerce.Repositories.ProductRepo;
import com.eshop.Ecommerce.Service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO addProduct(Long CategoryId, Product product) {
        Category category = categoryRepo.findById(CategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category","categoryId",CategoryId));
        product.setImage("default.png");
        product.setCategory(category);
        double specialPrice = product.getPrice() - ((product.getPrice() * product.getDiscount()) / 100);
        product.setSpecialPrice(specialPrice); // assuming this exists
        Product savedProduct = productRepo.save(product);
        return modelMapper.map(savedProduct,ProductDTO.class);
    }

    @Override
    public ProductResponse getAllProducts() {
        List<Product> products = productRepo.findAll();
        List<ProductDTO> productDTOS=products.stream()
                .map(product -> modelMapper.map(product,ProductDTO.class))
                .toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProducts(productDTOS);
        return productResponse;
    }

    @Override
    public ProductResponse searchByCategory(Long CategoryId) {
        Category category = categoryRepo.findById(CategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category","categoryId",CategoryId));
        List<Product> products = productRepo.findByCategoryOrderByPriceAsc(category);
        List<ProductDTO> productDTOS=products.stream()
                .map(product -> modelMapper.map(product,ProductDTO.class))
                .toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProducts(productDTOS);
        return productResponse;
    }
}
