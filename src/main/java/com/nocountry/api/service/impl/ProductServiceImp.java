package com.nocountry.api.service.impl;

import com.nocountry.api.dto.SaveProduct;
import com.nocountry.api.exception.ObjectNotFoundException;
import com.nocountry.api.persistence.entity.Category;
import com.nocountry.api.persistence.entity.Product;
import com.nocountry.api.persistence.repository.CategoryRepository;
import com.nocountry.api.persistence.repository.ProductRepository;
import com.nocountry.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> findOneById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Product createOne(SaveProduct saveProduct) {
        Product product = new Product();

        product.setName(saveProduct.getName());
        product.setDescription(saveProduct.getDescription());
        product.setPrice(saveProduct.getPrice());
        product.setImage(saveProduct.getImage());
        product.setRanking(5);
        product.setStock(saveProduct.getStock());
        product.setTime(saveProduct.getTime());
        product.setStatus(Product.ProductStatus.ENABLED);

        Set<Category> categories = new HashSet<>();
        for(String categoryName : saveProduct.getCategories()){
            Category.CategoryType categoryType = Category.CategoryType.valueOf(categoryName.toUpperCase());
            Category category = categoryRepository.findByName(categoryType)
                    .orElseThrow(() -> new ObjectNotFoundException("Category not found: " + categoryName));
            categories.add(category);
        }

        product.setCategories(categories);

        return productRepository.save(product);
    }

    @Override
    public Product updateOneById(Long productId, SaveProduct saveProduct) {
        Product productDb = productRepository.findById(productId)
                    .orElseThrow(() -> new ObjectNotFoundException("Product not found with id " + productId));

        productDb.setName(saveProduct.getName());
        productDb.setDescription(saveProduct.getDescription());
        productDb.setPrice(saveProduct.getPrice());
        productDb.setImage(saveProduct.getImage());
        productDb.setStock(saveProduct.getStock());
        productDb.setTime(saveProduct.getTime());

        Set<Category> categories = new HashSet<>();
        for(String categoryName : saveProduct.getCategories()){
            Category.CategoryType categoryType = Category.CategoryType.valueOf(categoryName.toUpperCase());
            Category category = categoryRepository.findByName(categoryType)
                    .orElseThrow(() -> new ObjectNotFoundException("Category not found: " + categoryName));
            categories.add(category);
        }


        productDb.setCategories(categories);

        return productRepository.save(productDb);

    }

    @Override
    public Product updateProductStatus(Long productId, String status) {
        Product productDb = productRepository.findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException("Product not found with id " + productId));

        if("enabled".equalsIgnoreCase(status)){
            productDb.setStatus(Product.ProductStatus.ENABLED);
        } else if ("disabled".equalsIgnoreCase(status)) {
            productDb.setStatus((Product.ProductStatus.DISABLED));
        } else {
            throw new IllegalArgumentException("Invalid status: " + status);
        }

        return productRepository.save(productDb);

    }

    @Override
    public boolean deleteOneById(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            productRepository.deleteById(productId);
            return true;
        }
        return false;
    }

    @Override
    public List<Product> findByCategory(Long categoryId, Pageable pageable) {

        List<Product> categoryProducts = new ArrayList<>();

        List<Product> productsPage = productRepository.findAll(pageable).getContent();;

        for(Product product : productsPage){
            Set<Category> categories = product.getCategories();

            for(Category category : categories){
                if(category.getId() == categoryId ){
                    categoryProducts.add(product);
                }
            }

        }

        return categoryProducts;

    }

    @Override
    public List<Product> searchProducts(String name, Pageable pageable) {

        List<Product> searchedProducts = new ArrayList<>();

        List<Product> productsPage = productRepository.findAll(pageable).getContent();

        for(Product product : productsPage){
            String productName = product.getName().toLowerCase();

            if(productName.contains(name.toLowerCase())){
                searchedProducts.add(product);
            }
        }

        return searchedProducts;
    }
}
