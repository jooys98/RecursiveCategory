package com.example.category_study.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@ToString(exclude = {"category"})
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //mdpick 빼고 best 넣음

    // 상품 코드
    private String code;  // 24343233930


    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "price", nullable = false)
    private Integer price;


    // LongText
    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    @ColumnDefault("0")
    @Column(name = "del_flag")
    private Boolean delFlag;


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;      // 해당 category


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @Builder.Default
    private List<CategoryBridge> categoryBridges = new ArrayList<>();

}
