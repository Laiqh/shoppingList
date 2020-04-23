package com.javaguru.shoppinglist.controller;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable("id") Long id) {
        Product product = productService.findById(id);
        return product;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Product product, UriComponentsBuilder builder) {
        Long id = productService.add(product);
        return ResponseEntity.created(builder.path("api/v1/products/{id}").buildAndExpand(id).toUri()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        productService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
