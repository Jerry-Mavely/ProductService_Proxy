package com.example.productservice_proxy.Clients.FakeStore.Dto;

import com.example.productservice_proxy.Clients.IClientProductDto;
import com.example.productservice_proxy.dtos.RatingDto;
import com.example.productservice_proxy.models.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FakeStoreProductDto implements IClientProductDto {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String imageURL;
    private RatingDto rating;

    public FakeStoreProductDto(){

    }
    public FakeStoreProductDto(Product product){
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.imageURL = product.getImageURL();
        this.category = product.getCategory().getName();
    }
}
