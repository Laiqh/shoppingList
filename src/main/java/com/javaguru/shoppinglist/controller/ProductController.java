package com.javaguru.shoppinglist.controller;

import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<ProductDTO> productDTOs = productService.findAll();
        return ResponseEntity.ok(productDTOs);
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<List<ProductDTO>> findAllPageable(@RequestParam Integer page,
                                                            @RequestParam Integer size) {
        List<ProductDTO> productDTOs = productService.findAll(page, size);
        return ResponseEntity.ok(productDTOs);
    }

    @GetMapping(params = {"name"})
    public ResponseEntity<List<ProductDTO>> findProductByName(@RequestParam String name) {
        List<ProductDTO> productDTOs = productService.findByName(name);
        return ResponseEntity.ok(productDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable("id") Long id) {
        ProductDTO productDTO = productService.findById(id);
        return ResponseEntity.ok(productDTO);
    }

    @PostMapping
    public ResponseEntity create(@Validated({ProductDTO.Create.class}) @RequestBody ProductDTO productDTO,
                                 UriComponentsBuilder builder) {
        Long id = productService.save(productDTO);
        URI location = builder.path("api/v1/products/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity update(@Validated({ProductDTO.Update.class}) @RequestBody ProductDTO productDTO,
                                 UriComponentsBuilder builder) {
        Long id = productDTO.getId();
        productService.update(productDTO);

        URI location = builder.path("api/v1/products/{id}").buildAndExpand(id).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<Void>(headers, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
