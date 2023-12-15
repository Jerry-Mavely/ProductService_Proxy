package com.example.productservice_proxy.services.productServices;

import com.example.productservice_proxy.models.Product;

import java.util.List;

public interface IProductService {

    List<Product> getAllProducts();
    Product getSingleProduct(Long productId);
    Product addNewProduct(Product product);
    Product updateProduct(Long productId,Product product);
    Product deleteProduct(Long productId);


}
