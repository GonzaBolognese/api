package com.nocountry.api.service;

import com.nocountry.api.dto.SaveCategory;
import com.nocountry.api.persistence.entity.Category;
import com.nocountry.api.persistence.entity.Category.CategoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CategoryService {
    Category createOne(SaveCategory SaveCategory);


    Page<Category> findAll(Pageable pageable);
}
