package com.example.category_study.repository.querydsl;

import com.example.category_study.entity.Product;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.category_study.entity.QCategory.category;
import static com.example.category_study.entity.QCategoryBridge.categoryBridge;
import static com.example.category_study.entity.QProduct.product;
import static com.example.category_study.entity.enums.LastType.Y;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {
    //카테고리 로 조건에 맞는 해당 프로덕트 들을 조회
    private final JPAQueryFactory queryFactory;
    @Override
    public List<Product> findByProductsCategoryId(Long categoryId) {

        return queryFactory
                .selectFrom(product)
                .distinct()
                .join(product.categoryBridges, categoryBridge) // 프로덕트 - 카테고리 브릿지
                .join(categoryBridge.category, category) // 카테고리 브릿지 - 카테고리
                .where(eqChildCategoryId(categoryId).or
                        (eqSubCategoryId(categoryId))) //두 조건 중 하나에 일치하는 값이 반환된다
                .fetch();

    }
    //파라미터로 받은 카테고리 아이디가 child 카테고리 일때 (최하위 카테고리 아이디로 product 조회)
    private BooleanExpression eqChildCategoryId(Long childCategoryId) {
        if (childCategoryId == null) {
            return null;
        }
        return category.parent.isNotNull().and( // parent 가 null 이 아니면 -> sub or child
                category.id.eq(childCategoryId)).and( //카테고리 아이디 = 파라미터로 받은 childCategoryId
                category.lastType.eq(Y)); // child 카테고리는 lastType 이 Y 이므로
    }

    //파라미터로 받은 카테고리 아이디가 sub 카테고리 일때 (sub 카테고리 아이디로 product 조회)
    private BooleanExpression eqSubCategoryId(Long subCategoryId) {
        if (subCategoryId == null) {
            return null;
        }
        return category.parent.isNotNull().and( // parent 가 null 이 아니면 -> sub or child
                category.parent.id.eq(subCategoryId) //카테고리 parent 값 = 파라미터로 받은 subCategoryId
        );        // 즉 subCategory 에 해당하는 child 카테고리의 product 들이 리턴된다


    }
}
