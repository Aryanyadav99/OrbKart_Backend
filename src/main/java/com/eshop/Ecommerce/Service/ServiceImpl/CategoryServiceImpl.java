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
    public CategoryDTO createCategory(CategoryDTO  categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category savedCategory = categoryRepo.findByCategoryName(category.getCategoryName());
        if (savedCategory != null) {
            throw new APIException("Category with the name "+ category.getCategoryName()+ " already exists");
        }
        Category newsavedCategory=categoryRepo.save(category);
        return modelMapper.map(newsavedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) throws ResourceNotFoundException {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",categoryId));
        categoryRepo.delete(category);
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) throws ResourceNotFoundException {
        Category category=modelMapper.map(categoryDTO, Category.class);
        Category savedCategory= categoryRepo.findById(categoryId)
                                .orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",categoryId));
        category.setCategoryId(categoryId);
        savedCategory=categoryRepo.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }
}
