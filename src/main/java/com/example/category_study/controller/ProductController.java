package com.example.category_study.controller;

import com.example.category_study.dto.ProductDTO;
import com.example.category_study.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity <ProductDTO> getProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProduct(productId));
    }
}
