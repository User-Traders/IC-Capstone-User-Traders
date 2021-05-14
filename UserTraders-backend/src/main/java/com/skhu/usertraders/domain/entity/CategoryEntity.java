package com.skhu.usertraders.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "board_subcategory")
public class CategoryEntity {


    // 고유번호
    @Id
    @Column(name = "id")
    private Integer id;

    // 카테고리명
    @Column(name = "name")
    private String name;

    @Builder
    public CategoryEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

}

