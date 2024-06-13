package com.nocountry.api.service.impl;

import com.nocountry.api.dto.SaveCategory;
import com.nocountry.api.persistence.entity.Category;
import com.nocountry.api.persistence.repository.CategoryRepository;
import com.nocountry.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Category createOne(SaveCategory saveCategory) {
        if (categoryRepository.existsByName(saveCategory.getName())) {
            throw new IllegalArgumentException("Category already exists: " + saveCategory.getName());
        }
        Category category = new Category();
        category.setName(saveCategory.getName());

        return categoryRepository.save(category);
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }


}
