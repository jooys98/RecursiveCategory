package com.example.category_study.service;

import com.example.category_study.dto.CategoryParentDTO;
import com.example.category_study.dto.CategoryResponseDTO;
import com.example.category_study.dto.ProductDTO;
import com.example.category_study.entity.Category;
import com.example.category_study.entity.Product;
import com.example.category_study.repository.CategoryRepository;
import com.example.category_study.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;


    @Override
    public List<CategoryResponseDTO> getParentCategories() {
        List<Category> mainCategories = categoryRepository.findMainCategories();
        return mainCategories.stream().map(this::entityToCategoriesDTO).toList();
    }

    @Override
    public List<CategoryResponseDTO> getSubCategoryList(Long mainCategoryId) {
        List<Category> subCategories = categoryRepository.findSubCategories(mainCategoryId);
        return subCategories.stream().map(this::entityToCategoriesDTO).toList();
    }


    @Override
    public List<CategoryResponseDTO> getChildCategoryList(Long subCategoryId) {
        List<Category> childCategories = categoryRepository.findChildCategories(subCategoryId);
        return childCategories.stream().map(this::entityToCategoriesDTO).toList();
    }

    @Override
    public List<ProductDTO> getProductCategoryId(Long categoryId) {
        List<Product> productList = productRepository.findByProductsCategoryId(categoryId);
        return productList.stream().map(this::entityToDTO).toList();
    } // 람다식을 사용하여 각각의 요소들을 전달하여 하나씩 dto 로 변환 후 list 로 수집


    @Override
    public List<CategoryParentDTO> getAllCategories(Long categoryId) {
        List<Category> dtoLists = categoryRepository.findByCategoryId(categoryId);
        return dtoLists.stream()
                .map(this::entityToParentCategoriesDTO)
                .toList();
    }
}
