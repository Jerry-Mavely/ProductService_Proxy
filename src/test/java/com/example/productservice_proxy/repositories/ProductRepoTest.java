package com.example.productservice_proxy.repositories;

import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    /*in Test if @Transactional is used, then the data will not be saved in the DB.
    * It will always Roll back in Test*/
    @Transactional
    @Rollback(value = false)
    void saveProductAndCategory() {
        Categories categories = new Categories();
        categories.setName("Electronic9");
        categories.setDescription("Electronics_description9");
//        categoryRepo.save(categories);

        Product product = new Product();
        product.setTitle("Laptop9");
        product.setDescription("Laptop_description9");
        product.setCategory(categories);
        productRepo.save(product);
//        Product product2 = new Product();
//        product2.setTitle("Laptop2");
//        product2.setDescription("Laptop_description");
//        product2.setCategory(categories);


//        productRepo.save(product2);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void updatingRows() {
//        Categories categories = categoryRepo.findById(252L);
        Categories categories = new Categories();
        categories.setId(252L);
        categories.setName("Electronics1_Updated");
        categories.setDescription("Electronics_description1_updated");
        categoryRepo.save(categories);
//        Product product2 = new Product();
//        product2.setTitle("Laptop2");
//        product2.setDescription("Laptop_description");
//        product2.setCategory(categories);


//        productRepo.save(product2);
    }
    @Test
    @Transactional
    @Rollback(value = false)
    void InsertProductWithoutAddingCategory() {
        Categories category2 = categoryRepo.findByName("Electronic7");
        Categories category1 = categoryRepo.findById(252L);
        Product product = new Product();
        product.setTitle("Laptop10");
        product.setDescription("Laptop_description10");
        product.setCategory(category2);
        productRepo.save(product);

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void testingQueries() {
//        Product product = productRepo.findProductById(252L);
//        List<Product> list = productRepo.findAll();
//        Categories categories = new Categories();
//        categories.setName("Mens Clothing");
//        categories.setDescription("Mens Clothing");
//        categoryRepo.save(categories);
//
//        Product product = new Product();
//        product.setTitle("Tshirt");
//        product.setDescription("Tshirt_description9");
//        product.setCategory(categories);
//        productRepo.save(product);

//        Categories category = categoryRepo.findById(categories.getId()).get();
        Categories category1 = categoryRepo.findById(602L);
        List<Product> lst = category1.getProductList();
        System.out.println(lst.get(0).getTitle());
        System.out.println("debug");

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void findSingleProduct(){
        Product product = productRepo.findProductById(252L);
        System.out.println(product.getCategory().getId());
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void deletingData(){
        List<Product> products= productRepo.findAll();
        for(Product product: products){
            if(product.getId() != 1L)
                productRepo.deleteById(product.getId());
        }
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void deletingCategoryData(){
        List<Categories> categories= categoryRepo.findAll();
        for(Categories category: categories){
            productRepo.deleteById(category.getId());
        }
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void addProductExistingCategory(){
        Product product = new Product();
        product.setTitle("Crucial 8GB DDR4 Ram Stick");
        product.setDescription("High performance 3200hz ram stick for Desktop PC");
        product.setPrice(2250.45);
        product.setImageURL("null");
        Categories category = categoryRepo.findById(1L);
        product.setCategory(category);
        productRepo.save(product);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void addCategory(){
        Categories categories = new Categories();
        categories.setName("Electronics");
        categories.setDescription("Electronics Category Products");
        categoryRepo.save(categories);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void testingQueries2() {
//        Product product = productRepo.findProductById(252L);
//        List<Product> list = productRepo.findAll();
//        Categories categories = new Categories();
//        categories.setName("Mens Clothing");
//        categories.setDescription("Mens Clothing");
//        categoryRepo.save(categories);
//
//        Product product = new Product();
//        product.setTitle("Tshirt");
//        product.setDescription("Tshirt_description9");
//        product.setCategory(categories);
//        productRepo.save(product);

//        Categories category = categoryRepo.findById(categories.getId()).get();
        Categories category1 = categoryRepo.findCategoriesByName("electronics");
        if(category1 == null){
            System.out.println("Category is null");
        }
        else System.out.println(category1.getId());

    }

    @Test
    void adhoc(){
        String s="Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.";
        System.out.println(s.length());
    }

    @Test
    void test_nullProductFetch(){
        Product product = productRepo.findProductById(2L);
        assertNull(product);
    }

}