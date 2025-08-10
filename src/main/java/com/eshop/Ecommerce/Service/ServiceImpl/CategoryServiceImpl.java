package com.eshop.Ecommerce.Service.ServiceImpl;

import com.eshop.Ecommerce.Model.Category;
import com.eshop.Ecommerce.Service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    final List<Category> categoryList=new ArrayList<>();
    private Long categoryId=1L;
    @Override
    public List<Category> getAllCategory() {
        return categoryList;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(categoryId++);
        categoryList.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category=categoryList.stream()
                .filter(c->c.getCategoryId().equals(categoryId))
                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not found"));
         categoryList.remove(category);
        return "Category Deleted Successfully";
    }

    @Override
    public Category updateCategory(Long categoryId, Category category) {
        Optional<Category> optionalCategory=categoryList.stream()
                .filter(c->c.getCategoryId().equals(categoryId))
                .findFirst();
        if(optionalCategory.isPresent()){
            Category existingCategory=optionalCategory.get();
            existingCategory.setCategoryName(category.getCategoryName());
            return existingCategory;
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not found");
        }
    }
}
