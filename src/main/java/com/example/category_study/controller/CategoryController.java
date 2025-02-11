package com.example.category_study.controller;

import com.example.category_study.dto.CategoryParentDTO;
import com.example.category_study.dto.CategoryResponseDTO;
import com.example.category_study.dto.ProductDTO;
import com.example.category_study.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;


    //최상위 카테고리 조회
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getParentCategories() {
        return ResponseEntity.ok(categoryService.getParentCategories());
    }

    //메인 카테고리 별 서브 카테고리 list
    @GetMapping("/{mainCategoryId}/sub/list")
    public ResponseEntity <List<CategoryResponseDTO>>getSubCategory(@PathVariable Long mainCategoryId) {
        return ResponseEntity.ok(categoryService.getSubCategoryList(mainCategoryId));
    }

    //서브 카테고리 별 child 카테고리 list
    @GetMapping("/{subCategoryId}/child/list")
    public ResponseEntity<List<CategoryResponseDTO>> getChildCategory(@PathVariable Long subCategoryId) {
        return ResponseEntity.ok(categoryService.getChildCategoryList(subCategoryId));
    }

    // 카테고리 아이디(sub , child) 로 이에 해당하는 productList 가져오기
    @GetMapping("/{categoryId}/product/list")
    public ResponseEntity<List<ProductDTO>> getProductByCategoryId(@PathVariable Long categoryId) {
        return ResponseEntity.ok(categoryService.getProductCategoryId(categoryId));
    }

    //부모 카테고리를 클릭하면 해당 자식 카테고리들 까지 나오게 !
    @GetMapping("/{categoryId}")
    public ResponseEntity<List<CategoryParentDTO>> getCategories(@PathVariable Long categoryId) {
        return ResponseEntity.ok(categoryService.getAllCategories(categoryId));
    }

}
