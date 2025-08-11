package com.eshop.Ecommerce.Controller;

import com.eshop.Ecommerce.Configuration.AppConstants;
import com.eshop.Ecommerce.Exception.ResourceNotFoundException;
import com.eshop.Ecommerce.Model.Category;
import com.eshop.Ecommerce.Payload.CategoryDTO;
import com.eshop.Ecommerce.Payload.CategoryResponse;
import com.eshop.Ecommerce.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    //Get Mapping
    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam (name = "pageNumber" , defaultValue = AppConstants.Page_Number ,required = false) Integer pageNumber,
            @RequestParam (name ="pageSize",defaultValue = AppConstants.Page_Size,required = false) Integer pageSize
    ){
         CategoryResponse categories=categoryService.getAllCategory(pageNumber,pageSize);
        return ResponseEntity.ok(categories);
    }

    //Put categories to db
    @PostMapping("/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedCategoryDTO= categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.CREATED);
    }

    //delete category
    @DeleteMapping("admin/categories/{categoriesId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoriesId)  {
            CategoryDTO statusDTO=categoryService.deleteCategory(categoriesId);
            return ResponseEntity.ok(statusDTO);
    }

    //updating category
    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO,
                                                 @PathVariable Long categoryId) {

            CategoryDTO savedCategoryDTO=categoryService.updateCategory(categoryId,categoryDTO);

            return ResponseEntity.ok(savedCategoryDTO);
    }
}
