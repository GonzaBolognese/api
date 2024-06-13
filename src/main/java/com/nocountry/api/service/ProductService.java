package com.nocountry.api.service;

import com.nocountry.api.dto.SaveProduct;
import com.nocountry.api.persistence.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface ProductService {
    Page<Product> findAll(Pageable pageable);

    Optional<Product> findOneById( Long productId);

    Product createOne(SaveProduct saveProduct);

    Product updateOneById(Long productId, SaveProduct saveProduct);

    Product updateProductStatus(Long productId, String status);

    boolean deleteOneById(Long productId);

    List<Product> findByCategory(Long categoryId, Pageable pageable);

    List<Product> searchProducts(String name, Pageable pageable);
}
