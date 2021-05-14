package com.skhu.usertraders.dto.board;

import com.skhu.usertraders.domain.entity.CategoryEntity;
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

    public CategoryDto convertEntityToDto(CategoryEntity categoryEntity) { //엔티티 객체 변수를 디티오 객체 변수로 변환
        return CategoryDto.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .build();
    }

}

