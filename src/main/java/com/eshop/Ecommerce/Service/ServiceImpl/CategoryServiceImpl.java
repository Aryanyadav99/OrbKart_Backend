package com.eshop.Ecommerce.Service.ServiceImpl;

import com.eshop.Ecommerce.Exception.APIException;
import com.eshop.Ecommerce.Exception.ResourceNotFoundException;
import com.eshop.Ecommerce.Model.Category;
import com.eshop.Ecommerce.Payload.CategoryDTO;
import com.eshop.Ecommerce.Payload.CategoryResponse;
import com.eshop.Ecommerce.Repositories.CategoryRepo;
import com.eshop.Ecommerce.Service.CategoryService;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryResponse getAllCategory() {
        List<Category> categories = categoryRepo.findAll();
        if(categories.isEmpty()){
            throw new APIException("no category found");
        }
        List<CategoryDTO> categoryDTOS=categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        return categoryResponse;
    }

    @Override
    public void createCategory(Category category) {
        Category savedCategory = categoryRepo.findByCategoryName(category.getCategoryName());
        if (savedCategory != null) {
            throw new APIException("Category with the name "+ category.getCategoryName()+ " already exists");
        }
        categoryRepo.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) throws ResourceNotFoundException {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",categoryId));
        categoryRepo.delete(category);
        return "Category Deleted Successfully";
    }

    @Override
    public Category updateCategory(Long categoryId, Category category) throws ResourceNotFoundException {
        Category savedCategory= categoryRepo.findById(categoryId)
                                .orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",categoryId));
        category.setCategoryId(categoryId);
        savedCategory=categoryRepo.save(category);
        return savedCategory;
    }
}
