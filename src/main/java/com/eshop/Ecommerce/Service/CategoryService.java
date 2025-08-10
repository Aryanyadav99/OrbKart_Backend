package com.eshop.Ecommerce.Service;

import com.eshop.Ecommerce.Model.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategory();
    void createCategory(Category category);
    String deleteCategory(Long categoryId);

    Category updateCategory(Long categoryId, Category category);
}
