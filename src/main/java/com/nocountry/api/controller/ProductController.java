package com.nocountry.api.controller;

import com.nocountry.api.dto.SaveProduct;
import com.nocountry.api.persistence.entity.Product;
import com.nocountry.api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {


    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(Pageable pageable, @RequestParam(required = false) String name ) {

        if (name != null && !name.isEmpty()) {
            List<Product> productsList = productService.searchProducts(name, pageable);

            if(productsList.isEmpty()){
                return ResponseEntity.notFound().build();
            }

            // Convertir la lista en una página
            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), productsList.size());
            Page<Product> productsPage = new PageImpl<>( productsList.subList(start, end), pageable, productsList.size());

            return ResponseEntity.ok(productsPage);
        } else {
            Page<Product> productsPage = productService.findAll(pageable);

            if (productsPage.hasContent()) {
                return ResponseEntity.ok(productsPage);
            }

            return ResponseEntity.notFound().build();

        }

    }


    @GetMapping("/{productId}")
    public ResponseEntity<Product> findOneById(@PathVariable Long productId) {

        Optional<Product> product = productService.findOneById(productId);

        if(product.isPresent()){
            return ResponseEntity.ok(product.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> findByCategory(@PathVariable Long categoryId, Pageable pageable) {

        List<Product> productsPage = productService.findByCategory(categoryId, pageable);

        if(productsPage.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productsPage);


    }

    @PostMapping
    public ResponseEntity<Product> createOne (@RequestBody @Valid SaveProduct saveProduct){
        Product product = productService.createOne(saveProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateOneById (@PathVariable Long productId, @RequestBody @Valid SaveProduct saveProduct){
        Product product = productService.updateOneById(productId, saveProduct);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{productId}/status")
    public  ResponseEntity<Product> updateProductStatus(@PathVariable Long productId, @RequestParam String status){
        Product product = productService.updateProductStatus(productId, status);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteOneById(@PathVariable Long productId){
        Optional<Product> product = productService.findOneById(productId);
        boolean isDeleted = productService.deleteOneById(productId);


        if(isDeleted){
            return new ResponseEntity<>("El producto:  \"" + product.get().getName() + "\" con Id: \"" + product.get().getId()  + "\" fue eliminado con éxito", HttpStatus.OK);
        }else {
            return new ResponseEntity<>( "El producto con Id: \"" + productId + "\" no fue encontrado."  ,HttpStatus.NOT_FOUND);
        }

    }

}
