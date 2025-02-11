package com.example.category_study.dto;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CategoryResponseDTO {
    private Long categoryId;
    private String name;
    private String logo;

}
