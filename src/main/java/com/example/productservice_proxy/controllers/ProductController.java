package com.example.productservice_proxy.controllers;

import com.example.productservice_proxy.Clients.FakeStore.Client.FakeStoreClient;
import com.example.productservice_proxy.Clients.FakeStore.Dto.FakeStoreProductDto;
import com.example.productservice_proxy.dtos.productDtos.ProductDto;
import com.example.productservice_proxy.dtos.productDtos.ProductResponseDto;
import com.example.productservice_proxy.dtos.productDtos.ProductsAndSizeDto;
import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;
import com.example.productservice_proxy.services.productServices.IProductService;
import com.example.productservice_proxy.services.productServices.SelfProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*This Controller will always answer "/products" */
@RestController
@RequestMapping("/products")
public class ProductController {

    IProductService productService;

    /** FakeStoreClient is a RestTemplate client that fetches data from a fake store API
     * Required to populate the table with data
     */
//    FakeStoreClient fakeStoreClient;

    /* Next line to be used when populating tables from FakeStore Api*/
//    public ProductController(IProductService productService, FakeStoreClient fakeStoreClient) {
    public ProductController(IProductService productService, FakeStoreClient fakeStoreClient) {
        this.productService = productService;
//        this.fakeStoreClient = fakeStoreClient;
    }

    @GetMapping("")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        List<ProductResponseDto> response=new ArrayList<>();
        Long i=1L;
        for(Product product: products){
            response.add(new ProductResponseDto(product,i));
            i++;
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/withSize")
    public ResponseEntity<ProductsAndSizeDto> getAllProductsWithSize(){
        ResponseEntity<List<ProductResponseDto>> productList = getAllProducts();
        ProductsAndSizeDto productsAndSizeDto = new ProductsAndSizeDto();
        productsAndSizeDto.setSize(productList.getBody().size());
        productsAndSizeDto.setProducts(productList.getBody());
        return new ResponseEntity<>(productsAndSizeDto, HttpStatus.OK);
    }

    @GetMapping("/{ID}")
    public ResponseEntity<ProductResponseDto> getSingleProduct(@PathVariable("ID") Long productId) /*throws Exception */{
        try {
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Accept", "application/json");
            headers.add("Content-Type", "application/json");
            headers.add("auth-token", "heyAccess");
            if(productId < 1) {
                throw new IllegalArgumentException("id less than 1");
            }
            ProductResponseDto product = new ProductResponseDto( productService.getSingleProduct(productId));
            ResponseEntity<ProductResponseDto> responseEntity = new ResponseEntity<>
                                                (product, headers, HttpStatus.OK);
            return responseEntity;
        }
        catch (Exception e) {
            throw e;
        }
//        catch (Exception e){
//            ResponseEntity<Product> responseEntity = new ResponseEntity<>
//                                                (HttpStatus.INTERNAL_SERVER_ERROR);
////            System.out.println(e.getMessage());
//            return responseEntity; // 500 error code
//        }
    }

    @PostMapping("")
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto productDto){
        Product response = productService.addNewProduct(
                                                getProduct(productDto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/multiple")
    public ResponseEntity<String> addMultipleNewProducts(@RequestBody List<ProductDto> listProducts){
        List<Product> products = new ArrayList<>();
        for(ProductDto productDto: listProducts){
            products.add(getProduct(productDto));
        }

        try {
            ((SelfProductService)productService).addMultipleNewProduct(products);
            return new ResponseEntity<>("Products added successfully", HttpStatus.OK);
        }
        catch (Exception e){
            throw e;
        }

    }

    /**
     * Function/Post Mapping to populate the tables with data from Fakestore API
     */
//    @PostMapping("/FS_Populate")
    /*
    public ResponseEntity<String> PopulateProducts(){
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto productDto : fakeStoreClient.getAllProducts()) {
            Product product = getProduct(productDto);
            products.add(product);
        }
        try {
            ((SelfProductService)productService).addMultipleNewProduct(products);
            return new ResponseEntity<>("Products added successfully", HttpStatus.OK);
        }
        catch (Exception e){
            throw e;
        }

    }
    */


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
//        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        int l = productDto.getDescription().length();
        product.setDescription(productDto.getDescription().substring(0,Math.min(500,l)));
        product.setImageURL(productDto.getImage());
        Categories category = new Categories();
        category.setName(productDto.getCategory());
//        category.setId(productDto.getCategoryId());
        product.setCategory(category);
        return product;
    }

    private Product getProduct(FakeStoreProductDto productDto) {
        Product product = new Product();
//        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        int l = productDto.getDescription().length();
        product.setDescription(productDto.getDescription().substring(0,Math.min(499,l)));
        product.setImageURL(productDto.getImage());
        Categories category = new Categories();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        return product;
    }

}
