package com.example.productservice_proxy.dtos.categoriesDtos;

import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CategoryListResponseDto {
    private String name;
    private String description;

    public CategoryListResponseDto() {
    }
    public CategoryListResponseDto(Categories categories) {
        this.name = categories.getName();
        this.description = categories.getDescription();

    }
}
