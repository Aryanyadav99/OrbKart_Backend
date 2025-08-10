package com.eshop.Ecommerce.Controller;

import com.eshop.Ecommerce.Model.Category;
import com.eshop.Ecommerce.Service.CategoryService;
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
    public ResponseEntity<List<Category>> getAllCategories(){
         List<Category> categories=categoryService.getAllCategory();
        return ResponseEntity.ok(categories);
    }

    //Put categories to db
    @PostMapping("/public/categories")
    public ResponseEntity<String> createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category Created Successfully", HttpStatus.CREATED);
    }

    //delete category
    @DeleteMapping("admin/categories/{categoriesId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoriesId){
        try{
            String status=categoryService.deleteCategory(categoriesId);
            return ResponseEntity.ok(status);
        }
        catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }

    //updating category
    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category ,
                                                 @PathVariable Long categoryId){
        try{
            Category savedCategory=categoryService.updateCategory(categoryId,category);
            return ResponseEntity.ok("category updated successfully");
        }
        catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }
}
