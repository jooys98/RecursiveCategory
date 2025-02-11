package com.example.category_study.repository.querydsl;

import com.example.category_study.entity.Category;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.category_study.entity.QCategory.category;

@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    //서브카테고리로 미니 카테고리 조회
    @Override
    public List<Category> findChildCategories(Long subCategoryId) {
        return queryFactory
                .selectFrom(category)
                .where(category.parent.id.eq(subCategoryId))
                .fetch();
    }

    @Override
    //메인카테고리 아이디로 나머지 카테고리 한번에 조회
    public List<Category> findByCategoryId(Long categoryId) {
        return queryFactory
                .selectFrom(category)
                .where(category.parent.id.eq(categoryId))
                .fetch();
    }

    //메인카테고리 아이디로 서브카테고리 조회
    @Override
    public List<Category> findSubCategories(Long mainCategoryId) {
        return queryFactory
                .selectFrom(category)
                .where(category.parent.id.eq(mainCategoryId))
                .fetch();
    }

    //메인 카테고리만 조회
    @Override
    public List<Category> findMainCategories() {
        return queryFactory
                .selectFrom(category)
                .where(category.parent.id.isNull())
                .fetch();
    }


}
