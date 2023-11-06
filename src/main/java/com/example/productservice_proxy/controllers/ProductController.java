package com.example.productservice_proxy.controllers;

import com.example.productservice_proxy.dtos.ProductDto;
import com.example.productservice_proxy.services.IProductService;
import com.example.productservice_proxy.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*This Controller will always answer "/products" */
@RestController
@RequestMapping("/products")
public class ProductController {


    IProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String getAllProducts(){


        return "Getting all Products after changes";

    }

    @GetMapping("/{ID}")
    public String getSingleProduct(@PathVariable("ID") Long productId){
        productService.getSingleProduct(productId);
        return "Returning Single Product with ID " + productId;
    }

    @PostMapping("")
    public String addNewProduct(@RequestBody ProductDto productDto){
        return "Adding new Product "+productDto.getTitle()+" <> " + productDto;
    }

    @PutMapping("/{ID}")
    public String updateProduct(@PathVariable("ID") Long Id){
        return "Updating Product with ID " + Id;
    }

    @DeleteMapping("/{ID}")
    public String deleteProduct(@PathVariable("ID") Long Id){
        return "Deleting Product with ID " + Id;
    }

    @GetMapping("/getProduct/{ID}")
    public ProductDto addNewProduct2(@PathVariable("ID") Long Id){
        ProductDto productDto = new ProductDto();
//        productDto.setCategory("Category");
        productDto.setTitle("Product Title");
        productDto.setPrice(100.0);
//        productDto.setDescription("Product Description");
        return productDto;
    }


}
