package com.example.productservice_proxy.repositories;

import com.example.productservice_proxy.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

//    @Override
    Product save(Product product);
    Product findProductById(Long Id);
    Product findByPriceBetween(double greaterThan, double lessThan);
    List<Product> findAll();
    void deleteById(Long id);

}
