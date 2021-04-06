package com.skhu.usertraders.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CategoryDto {

    // 고유번호
    private Integer id;

    // 카테고리명
    private String name;

    @Builder
    public CategoryDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

}

