package com.example.category_study.repository.querydsl;

import com.example.category_study.entity.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> findByProductsCategoryId(Long categoryId);
}
