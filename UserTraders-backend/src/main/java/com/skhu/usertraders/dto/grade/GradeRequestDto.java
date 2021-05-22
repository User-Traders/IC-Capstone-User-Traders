package com.skhu.usertraders.dto.grade;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GradeRequestDto {

    private Integer grade;

    private String recvUserId;

}
