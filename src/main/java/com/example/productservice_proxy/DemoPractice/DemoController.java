package com.example.productservice_proxy.DemoPractice;


import com.example.productservice_proxy.dtos.ProductDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Test")
public class DemoController {
    private DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/TestProducts")
    public List<ProductDto> testGetAllProducts() {
        return demoService.getProducts1by1();
    }

    @GetMapping("/TestProducts2")
    public List<ProductDto> testGetAllProducts2() {
        return demoService.getProductsAll();
    }

    @GetMapping("/TestProducts3")
    public List<ProductDto> testGetAllProducts3() {
        return demoService.getProductsAll2();
    }

    @GetMapping("/TestProducts4")
    public ProductDto[] testGetAllProducts4() {
        return demoService.getProductsAll3();
    }

    @PostMapping("/hello")
    public String hello2() {
        return "hello World 2";
    }

}
