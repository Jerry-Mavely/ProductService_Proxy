package com.example.productservice_proxy.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String title;
    private double price;
    @Column(length = 500)
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    private Categories category;
    private String imageURL;
}
