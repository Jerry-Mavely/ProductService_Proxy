package com.example.productservice_proxy.controllers;

import com.example.productservice_proxy.dtos.categoriesDtos.CategoryListResponseDto;
import com.example.productservice_proxy.dtos.categoriesDtos.SingleCategoryResponseDto;
import com.example.productservice_proxy.dtos.productDtos.ProductResponseDto;
import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;
import com.example.productservice_proxy.services.categoryServices.ICategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {

    ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<List<CategoryListResponseDto>> getAllCategories(){
        List<Categories> categories = categoryService.getAllCategories();
        List<CategoryListResponseDto> response = new ArrayList<>();
        for(Categories category: categories){
            response.add(new CategoryListResponseDto(category));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<SingleCategoryResponseDto> getProductsInCategory(@PathVariable("categoryId") Long categoryId){
        Categories category = categoryService.getSingleCategory(categoryId);
        SingleCategoryResponseDto response = new SingleCategoryResponseDto(category);
//        response.setProductList(new ArrayList<>());
        Long i=1L;
        for(Product product : category.getProductList()){
            response.getProductList().add(new ProductResponseDto(product,i));
            i++;
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
