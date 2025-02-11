package com.example.category_study.repository;

import com.example.category_study.entity.Category;
import com.example.category_study.repository.querydsl.CategoryRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {
}
