package com.example.category_study.service;

import com.example.category_study.dto.ProductDTO;
import com.example.category_study.entity.Product;

import java.util.List;

public interface ProductService {


    ProductDTO getProduct(Long productId);
}
