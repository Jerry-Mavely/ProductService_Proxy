package com.example.productservice_proxy.dtos.categoriesDtos;

import com.example.productservice_proxy.dtos.productDtos.ProductResponseDto;
import com.example.productservice_proxy.models.Categories;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class SingleCategoryResponseDto {
    private String name;
    private String description;
    private List<ProductResponseDto> productList;

    public SingleCategoryResponseDto() {
    }
    public SingleCategoryResponseDto(Categories category) {
        this.name = category.getName();
        this.description = category.getDescription();
        this.productList = new ArrayList<>();
    }
}
