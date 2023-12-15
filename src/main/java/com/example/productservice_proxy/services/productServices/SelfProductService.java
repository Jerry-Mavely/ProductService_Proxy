package com.example.productservice_proxy.services.productServices;

import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;
import com.example.productservice_proxy.repositories.CategoryRepo;
import com.example.productservice_proxy.repositories.ProductRepo;
import com.example.productservice_proxy.services.categoryServices.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelfProductService implements IProductService{

    ProductRepo productRepo;
    ICategoryService categoryService;

    public SelfProductService(ProductRepo productRepo,ICategoryService categoryService) {
        this.productRepo = productRepo;
        this.categoryService = categoryService;
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepo.findAll();
    }

    @Override
    public Product getSingleProduct(Long productId) {
        return this.productRepo.findProductById(productId);
    }

    @Override
    public Product addNewProduct(Product product) {
        String categoryName = product.getCategory().getName();
        Categories testCategory= categoryService.findCategoriesByName(categoryName);
        if (testCategory != null) {
            product.setCategory(testCategory);
        }
        this.productRepo.save(product);
        return product;
    }


    public void addMultipleNewProduct(List<Product> products) {
        try {
            for (Product product : products) {
                String categoryName = product.getCategory().getName();
                Categories testCategory= categoryService.findCategoriesByName(categoryName);
                if (testCategory != null) {
                    product.setCategory(testCategory);
                }
                this.productRepo.save(product);
            }
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(Long productId) {
        return null;
    }
}
