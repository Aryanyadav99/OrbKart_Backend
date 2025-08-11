package com.eshop.Ecommerce.Service;

import com.eshop.Ecommerce.Exception.ResourceNotFoundException;
import com.eshop.Ecommerce.Model.Category;
import com.eshop.Ecommerce.Payload.CategoryDTO;
import com.eshop.Ecommerce.Payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    public CategoryResponse getAllCategory();
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    String deleteCategory(Long categoryId) throws ResourceNotFoundException;

    Category updateCategory(Long categoryId, Category category) throws ResourceNotFoundException;
}
