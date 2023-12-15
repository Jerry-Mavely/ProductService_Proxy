package com.example.productservice_proxy.dtos.productDtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ProductsAndSizeDto {
    private int size;
    private List<ProductResponseDto> products;
}
