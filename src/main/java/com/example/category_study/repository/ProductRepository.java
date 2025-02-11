package com.example.category_study.repository;

import com.example.category_study.entity.Product;
import com.example.category_study.repository.querydsl.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> , ProductRepositoryCustom {




}
