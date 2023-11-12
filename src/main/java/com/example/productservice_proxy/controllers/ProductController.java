package com.example.productservice_proxy.controllers;

import com.example.productservice_proxy.Clients.FakeStore.Dto.FakeStoreProductDto;
import com.example.productservice_proxy.Clients.IClientProductDto;
import com.example.productservice_proxy.dtos.ProductDto;
import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;
import com.example.productservice_proxy.services.IProductService;
import com.example.productservice_proxy.services.FakeStoreProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*This Controller will always answer "/products" */
@RestController
@RequestMapping("/products")
public class ProductController {

    IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{ID}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("ID") Long productId){
        try {
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Accept", "application/json");
            headers.add("Content-Type", "application/json");
            headers.add("auth-token", "heyAccess");
            if(productId < 1) {
                throw new IllegalArgumentException("Something went wrong");
            }
            Product product = productService.getSingleProduct(productId);
            ResponseEntity<Product> responseEntity = new ResponseEntity<>
                                                (product, headers, HttpStatus.OK);
            return responseEntity;
//        System.out.println(s);
        }
        catch (Exception e){
            ResponseEntity<Product> responseEntity = new ResponseEntity<>
                                                (HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity; // 500 error code
        }
    }

    @PostMapping("")
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto productDto){
        Product response = productService.addNewProduct(
                                                getProduct(productDto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{ID}")
    public String updateProduct(@PathVariable("ID") Long Id,
                                @RequestBody ProductDto productDto){
//        Product product = productService.updateProduct(Id,productDto);
        return "Updating Product with ID " + Id;
    }

    @PatchMapping("/{ID}")
    public ResponseEntity<Product> patchProduct(@PathVariable("ID") Long Id,
                               @RequestBody ProductDto productDto){

        Product response = productService.updateProduct(
                                            Id,getProduct(productDto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{ID}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("ID") Long Id){
        Product response = productService.deleteProduct(Id);

        return new ResponseEntity<>(response, HttpStatus.OK);
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
