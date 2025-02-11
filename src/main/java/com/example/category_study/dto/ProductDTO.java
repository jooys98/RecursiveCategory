package com.example.category_study.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProductDTO {

    private Long id;
    private String code;
    private Long categoryId;
    private String categoryName;
    private String name;
    private Integer price;
    private String description;

}
