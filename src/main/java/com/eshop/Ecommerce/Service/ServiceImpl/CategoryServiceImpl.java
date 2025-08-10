package com.eshop.Ecommerce.Service.ServiceImpl;

import com.eshop.Ecommerce.Model.Category;
import com.eshop.Ecommerce.Repositories.CategoryRepo;
import com.eshop.Ecommerce.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

    @Override
    public void createCategory(Category category) {
        categoryRepo.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category Not Found"));
        categoryRepo.delete(category);
        return "Category Deleted Successfully";
    }

    @Override
    public Category updateCategory(Long categoryId, Category category) {
        Category savedCategory= categoryRepo.findById(categoryId)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category Not Found"));
        category.setCategoryId(categoryId);
        savedCategory=categoryRepo.save(category);
        return savedCategory;
    }
}
