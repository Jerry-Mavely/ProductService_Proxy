package com.example.productservice_proxy.services.categoryServices;

import com.example.productservice_proxy.models.Categories;

import java.util.List;

public interface ICategoryService {
    List<Categories> getAllCategories();

    Categories getSingleCategory(Long categoryId);

    Categories findCategoriesByName(String categoryName);
}
