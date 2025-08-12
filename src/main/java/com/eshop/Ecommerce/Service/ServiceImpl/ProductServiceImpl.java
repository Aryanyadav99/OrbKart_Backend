package com.eshop.Ecommerce.Service.ServiceImpl;

import com.eshop.Ecommerce.Exception.ResourceNotFoundException;
import com.eshop.Ecommerce.Model.Category;
import com.eshop.Ecommerce.Model.Product;
import com.eshop.Ecommerce.Payload.ProductDTO;
import com.eshop.Ecommerce.Payload.ProductResponse;
import com.eshop.Ecommerce.Repositories.CategoryRepo;
import com.eshop.Ecommerce.Repositories.ProductRepo;
import com.eshop.Ecommerce.Service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO addProduct(Long CategoryId, ProductDTO productDTO) {
        Product product=modelMapper.map(productDTO,Product.class);
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

    @Override
    public ProductResponse searchProductByKeyword(String keyword) {
        List<Product> products = productRepo.findByProductNameLikeIgnoreCase('%'+keyword+'%');
        List<ProductDTO> productDTOS=products.stream()
                .map(product -> modelMapper.map(product,ProductDTO.class))
                .toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProducts(productDTOS);
        return productResponse;
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
        Product product=modelMapper.map(productDTO,Product.class);
        // get the product form db
        Product existingproduct=productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("product","productId",productId));
        //update the product info with new product
        existingproduct.setProductName(product.getProductName());
        existingproduct.setDescription(product.getDescription());
        existingproduct.setQuantity(product.getQuantity());
        existingproduct.setDiscount(product.getDiscount());
        existingproduct.setPrice(product.getPrice());
        existingproduct.setSpecialPrice(product.getSpecialPrice());
        // save to db
        Product savedProduct=productRepo.save(existingproduct);
        return modelMapper.map(product,ProductDTO.class);
    }

    @Override
    public ProductDTO deleteProduct(Long productId) {
        Product deletableProduct = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("product","productId",productId));
        productRepo.delete(deletableProduct);
        return modelMapper.map(deletableProduct,ProductDTO.class);
    }

    @Override
    public ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException {
        //Get the product from DB
        Product product=productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("product","productId",productId));
        //upload image to server
        //get the file name of uploaded image
        String path="images/";
        String fileName=uploadImage(path,image);
        //updating the new file name to the product
        product.setImage(fileName);

        //save updated product
        Product savedProduct=productRepo.save(product);

        // return DTO after mapping product to DTO
        return modelMapper.map(savedProduct,ProductDTO.class);
    }

    private String uploadImage(String path, MultipartFile file) throws IOException {
        //file names of original file
        String originalFilename = file.getOriginalFilename();

        //generate a unique file name via random uuid
        String randomId= UUID.randomUUID().toString();
            //logic  filename.jpg and id= 1234 --> 1234.jpg
        String fileName=randomId.concat(originalFilename.substring(originalFilename.lastIndexOf('.')));
        String filePath=path+ File.separator+fileName;

        //check if path exist and create
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdir();
        }
        //upload to server
        Files.copy(file.getInputStream(), Paths.get(filePath));

        //return file name
        return fileName;
    }
}
