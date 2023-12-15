package com.example.productservice_proxy.services.categoryServices;

import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.repositories.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelfCategoryService implements ICategoryService {

    private CategoryRepo categoryRepo;

    public SelfCategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<Categories> getAllCategories() {
        return this.categoryRepo.findAll();
    }

    @Override
    public Categories getSingleCategory(Long categoryId) {
        Categories categories = this.categoryRepo.findCategoriesById(categoryId);
        categories.setProductList(categories.getProductList());
        return categories;
    }

    @Override
    public Categories findCategoriesByName(String categoryName){
        return this.categoryRepo.findCategoriesByName(categoryName);
    }

}
