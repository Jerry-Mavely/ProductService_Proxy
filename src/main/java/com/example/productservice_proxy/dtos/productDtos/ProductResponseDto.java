package com.example.productservice_proxy.dtos.productDtos;

import com.example.productservice_proxy.models.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductResponseDto {
    private Long itemNumber;
    private String title;
    private double price;
    private String description;
    private Long categoryId;
    private String category;
    private String imageURL;
//    private RatingDto rating;

    public ProductResponseDto(){

    }
    public ProductResponseDto(Product product,Long itemNumber){
        this.itemNumber = itemNumber;
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.imageURL = product.getImageURL();
        this.category = product.getCategory().getName();
    }
    public ProductResponseDto(Product product){
//        this.itemNumber = itemNumber;

        this.title = product.getTitle();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.imageURL = product.getImageURL();
        this.category = product.getCategory().getName();
    }
}
