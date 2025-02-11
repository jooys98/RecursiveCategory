package com.example.category_study.service;

import com.example.category_study.dto.ProductDTO;
import com.example.category_study.entity.Product;
import com.example.category_study.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Override
    public ProductDTO getProduct(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        return categoryService.entityToDTO(product);
    }
}
