package com.example.productservice_proxy.DemoPractice;


import com.example.productservice_proxy.dtos.ProductDto;
import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemoService {
    private RestTemplateBuilder restTemplateBuilder;

    public DemoService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public List<ProductDto> getProducts1by1() {
        List<ProductDto> products = new ArrayList<>();
        RestTemplate restTemplate = restTemplateBuilder.build();
        for (int i = 1; i < 10; i++) {
            ProductDto productDto = restTemplate.getForEntity(
                    "https://fakestoreapi.com/products/{id}",
                    ProductDto.class, i).getBody();
            products.add(productDto);
        }
        return products;
    }


    public List<ProductDto> getProductsAll() {
        List<ProductDto> products = new ArrayList<>();
        RestTemplate restTemplate = restTemplateBuilder.build();
        products = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                ArrayList.class);

        return products;
    }

    public List<ProductDto> getProductsAll2() {
        ArrayList<ProductDto> productDtos = new ArrayList<>();
        RestTemplate restTemplate = restTemplateBuilder.build();
        productDtos = restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                ArrayList.class).getBody();

        return productDtos;
    }

    public ProductDto[] getProductsAll3() {
        ProductDto[] productDtos;
        RestTemplate restTemplate = restTemplateBuilder.build();
        productDtos = restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                ProductDto[].class).getBody();

        return productDtos;
    }

    private Product getProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        Categories category = new Categories();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
