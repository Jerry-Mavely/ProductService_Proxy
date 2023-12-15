package com.example.productservice_proxy.dtos.productDtos;

import com.example.productservice_proxy.Clients.IClientProductDto;
import com.example.productservice_proxy.dtos.RatingDto;
import com.example.productservice_proxy.models.Categories;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto  {
    private Long id;
    private String title;
    private double price;
    private String description;
    private Long categoryId;
    private String category;
    private String image;
    private RatingDto rating;
}
