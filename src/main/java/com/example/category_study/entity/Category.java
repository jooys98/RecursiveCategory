package com.example.category_study.entity;

import com.example.category_study.entity.enums.LastType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "category")
@ToString(exclude = "children")

//엔티티의 자가 참조(Self-Referential) 관계
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "logo")
    private String logo;

    //부모 참조
    //각각의 자식 카테고리는 하나의 부모를 가짐
    //여러개의 부모 카테고리 들도 또 하나의 부모 카테고리를 가질 수 있음
    @ManyToOne(fetch = FetchType.LAZY)

    //각 카테고리는 자신을 참조하는 parent_id 필드를 가짐
    //이 parent_id는 같은 테이블의 id를 참조
    @JoinColumn(name="parent_id")
    private Category parent;

    //자식은 부모를 참조 하는 리스트
    //하나의 카테고리는 여러 자식 카테고리를 만들 수 있음
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Category> children = new ArrayList<>();

    //엔티티 증식의 끝을 나태니기 위한 enums 값
    //Y : 이에 해당하는 카테고리는 자식 카테고리가 없음
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'N'") // 자식 카테고리 존재
    private LastType lastType;



    // 카테고리 브릿지
    @OneToMany(mappedBy = "category")
    @Builder.Default
    private List<CategoryBridge> categoryBridgeList = new ArrayList<>();

}
