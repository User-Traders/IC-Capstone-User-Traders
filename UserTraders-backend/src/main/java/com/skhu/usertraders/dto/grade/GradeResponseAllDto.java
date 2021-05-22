package com.skhu.usertraders.dto.grade;

import com.skhu.usertraders.domain.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class GradeResponseAllDto {
    private Integer id;
    private UserEntity sentId;
    private UserEntity recvId;
    private Integer grade;

    @Builder
    public GradeResponseAllDto(Integer id, UserEntity sentId, UserEntity recvId, Integer grade) {
        this.id = id;
        this.sentId = sentId;
        this.recvId = recvId;
        this.grade = grade;
    }
}
