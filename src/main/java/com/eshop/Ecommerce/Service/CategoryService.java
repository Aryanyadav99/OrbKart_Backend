package com.eshop.Ecommerce.Service;

import com.eshop.Ecommerce.Exception.ResourceNotFoundException;
import com.eshop.Ecommerce.Payload.CategoryDTO;
import com.eshop.Ecommerce.Payload.CategoryResponse;

public interface CategoryService {
    public CategoryResponse getAllCategory(Integer pageNumber, Integer pageSize,String sortBy,String sortOrder) throws ResourceNotFoundException;
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO deleteCategory(Long categoryId) throws ResourceNotFoundException;

    CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) throws ResourceNotFoundException;
}

