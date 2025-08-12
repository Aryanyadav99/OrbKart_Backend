package com.eshop.Ecommerce.Service.ServiceImpl;

import com.eshop.Ecommerce.Exception.APIException;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Value("${product.image}")
    private String path;
    @Override
    public ProductDTO addProduct(Long CategoryId, ProductDTO productDTO) {
        Category category = categoryRepo.findById(CategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category","categoryId",CategoryId));
        boolean isProductNotPresent=true;
        List<Product> products =category.getProducts();
        for (Product value : products) {
            if (value.getProductName().equals(productDTO.getProductName())) {
                isProductNotPresent = false;
                break;
            }
        }
        if(!isProductNotPresent){
            throw new APIException("Product already present in Category");
        }
        else{
            Product product=modelMapper.map(productDTO,Product.class);
            product.setImage("default.png");
            product.setCategory(category);
            double specialPrice = product.getPrice() - ((product.getPrice() * product.getDiscount()) / 100);
            product.setSpecialPrice(specialPrice); // assuming this exists
            Product savedProduct = productRepo.save(product);
            return modelMapper.map(savedProduct,ProductDTO.class);
        }
    }

    @Override
    public ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAnyOrder = sortOrder.equalsIgnoreCase("asc")
                ?Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAnyOrder);
        Page<Product> productPage = productRepo.findAll(pageDetails);

        List<Product> products = productPage.getContent();
        if(products.isEmpty()){
            throw  new APIException("Not any products present");
        }
        List<ProductDTO> productDTOS=products.stream()
                .map(product -> modelMapper.map(product,ProductDTO.class))
                .toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setProducts(productDTOS);
        productResponse.setPageNumber(productPage.getNumber());
        productResponse.setPageSize(productPage.getSize());
        productResponse.setTotalElements(productPage.getTotalElements());
        productResponse.setTotalPages(productPage.getTotalPages());
        productResponse.setLastPage(productPage.isLast());
        return productResponse;
    }

    @Override
    public ProductResponse searchByCategory(Long CategoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Category category = categoryRepo.findById(CategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category","categoryId",CategoryId));

        Sort sortByAnyOrder = sortOrder.equalsIgnoreCase("asc")
                ?Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAnyOrder);
        Page<Product> productPage = productRepo.findByCategoryOrderByPriceAsc(category,pageDetails);
        List<Product> products = productPage.getContent();
        List<ProductDTO> productDTOS=products.stream()
                .map(product -> modelMapper.map(product,ProductDTO.class))
                .toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProducts(productDTOS);
        productResponse.setPageNumber(productPage.getNumber());
        productResponse.setPageSize(productPage.getSize());
        productResponse.setTotalElements(productPage.getTotalElements());
        productResponse.setTotalPages(productPage.getTotalPages());
        productResponse.setLastPage(productPage.isLast());
        return productResponse;
    }

    @Override
    public ProductResponse searchProductByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAnyOrder = sortOrder.equalsIgnoreCase("asc")
                ?Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAnyOrder);
        Page<Product> productPage = productRepo.findByProductNameLikeIgnoreCase('%'+keyword+'%',pageDetails);
        List<Product> products=productPage.getContent();
        List<ProductDTO> productDTOS=products.stream()
                .map(product -> modelMapper.map(product,ProductDTO.class))
                .toList();
        if(products.isEmpty()){
            throw  new APIException("Not any products present with this keyword");
        }
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProducts(productDTOS);
        productResponse.setPageNumber(productPage.getNumber());
        productResponse.setPageSize(productPage.getSize());
        productResponse.setTotalElements(productPage.getTotalElements());
        productResponse.setTotalPages(productPage.getTotalPages());
        productResponse.setLastPage(productPage.isLast());
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
