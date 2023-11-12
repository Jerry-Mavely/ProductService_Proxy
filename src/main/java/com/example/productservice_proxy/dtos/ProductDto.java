package com.example.productservice_proxy.dtos;

import com.example.productservice_proxy.Clients.IClientProductDto;
import com.example.productservice_proxy.models.Categories;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto implements IClientProductDto {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String imageURL;
    private RatingDto rating;
}
