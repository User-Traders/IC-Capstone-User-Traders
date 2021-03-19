package com.skhu.usertraders.dto;


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
}
