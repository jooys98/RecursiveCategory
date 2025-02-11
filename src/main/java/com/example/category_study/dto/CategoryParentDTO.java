package com.example.category_study.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CategoryParentDTO {

    private Long categoryId;
    private String name;

    @Builder.Default
    private Set<CategoryParentDTO> children = new HashSet<>();
    private String logo;


}
