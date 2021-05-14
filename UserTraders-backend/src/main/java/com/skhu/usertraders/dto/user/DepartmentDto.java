package com.skhu.usertraders.dto.user;


import com.skhu.usertraders.domain.entity.DepartmentEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DepartmentDto {

    // 학과번호
    private Integer id;

    // 학과명
    private String name;


    @Builder
    public DepartmentDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public DepartmentDto convertEntityToDto(DepartmentEntity departmentEntity) { //엔티티 객체 변수를 디티오 객체 변수로 변환
        return DepartmentDto.builder()
                .id(departmentEntity.getId())
                .name(departmentEntity.getName())
                .build();
    }
}
