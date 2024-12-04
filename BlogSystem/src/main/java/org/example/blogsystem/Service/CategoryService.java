package org.example.blogsystem.Service;

import org.example.blogsystem.ApiResponse.ApiException;
import org.example.blogsystem.Model.Category;
import org.example.blogsystem.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    public void updateCategory(Integer id, Category category){

        Category c = categoryRepository.findCategoryByCategoryId(id);

        if(c == null)
            throw new ApiException("Category with this ID not found to update it!");

       c.setName(category.getName());

       categoryRepository.save(c);

    }

    public void deleteCategory(Integer id){

        Category c = categoryRepository.findCategoryByCategoryId(id);

        if(c == null)
            throw new ApiException("Category with this ID not found to update it!");

        categoryRepository.delete(c);
    }







}
