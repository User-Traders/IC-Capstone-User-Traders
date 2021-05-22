package com.skhu.usertraders.dto.grade;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GradeResponseAvggradeDto {

    private Integer grade;

    @Builder
    public GradeResponseAvggradeDto(Integer grade) {
        this.grade = grade;
   }
}
