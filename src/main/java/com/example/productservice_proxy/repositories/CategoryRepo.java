package com.example.productservice_proxy.repositories;

import com.example.productservice_proxy.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Categories, Long> {
    Categories save(Categories categories);
    Categories findCategoriesById(long id);
    Categories findById(long id);

    Categories findByName(String categoryName);
    Categories findCategoriesByName(String categoryName);
    void deleteById(Long id);

    @Query(value="SELECT c.name FROM Categories c WHERE c.id = ?1")
    String findByCategoryNameById(long id);
}
