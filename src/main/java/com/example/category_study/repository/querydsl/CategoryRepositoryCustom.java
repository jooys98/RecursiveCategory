package com.example.category_study.repository.querydsl;

import com.example.category_study.entity.Category;

import java.util.List;

public interface CategoryRepositoryCustom {
    List<Category> findChildCategories(Long subCategoryId);

    List<Category> findByCategoryId(Long categoryId);

    List<Category> findSubCategories(Long mainCategoryId);

    List<Category> findMainCategories();
}
