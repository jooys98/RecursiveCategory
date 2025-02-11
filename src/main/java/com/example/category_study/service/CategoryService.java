package com.example.category_study.service;

import com.example.category_study.dto.CategoryParentDTO;
import com.example.category_study.dto.CategoryResponseDTO;
import com.example.category_study.dto.ProductDTO;
import com.example.category_study.entity.Category;
import com.example.category_study.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public interface CategoryService {
    List<CategoryResponseDTO> getParentCategories();

    List<CategoryResponseDTO> getSubCategoryList(Long mainCategoryId);

    List<CategoryResponseDTO> getChildCategoryList(Long subCategoryId);

    List<ProductDTO> getProductCategoryId(Long categoryId);

    List<CategoryParentDTO> getAllCategories(Long categoryId);

    default CategoryResponseDTO entityToCategoriesDTO(Category category) {
        return CategoryResponseDTO.builder()
                .categoryId(category.getId())
                .name(category.getName())
                .logo(category.getLogo())
                .build();
    }

    default ProductDTO entityToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .code(product.getCode())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }

    default CategoryParentDTO entityToParentCategoriesDTO(Category category) {
        CategoryParentDTO dto = new CategoryParentDTO();
        dto.setCategoryId(category.getId());
        dto.setName(category.getName());
        dto.setLogo(category.getLogo());
        if (category.getChildren() != null && !category.getChildren().isEmpty()) {
            dto.setChildren(category.getChildren().stream().map(this::entityToParentCategoriesDTO).collect(Collectors.toSet()));
        }
        return dto;
    }

}

