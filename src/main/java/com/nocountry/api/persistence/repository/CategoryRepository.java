package com.nocountry.api.persistence.repository;

import com.nocountry.api.persistence.entity.Category;
import com.nocountry.api.persistence.entity.Category.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository <Category, Long> {

    Optional<Category> findByName(CategoryType name);

    boolean existsByName(Category.CategoryType name);
}
