package com.skhu.usertraders.dto.board;

import com.skhu.usertraders.domain.entity.BoardEntity;
import com.skhu.usertraders.domain.entity.UserEntity;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardResponseUserIdDto {

    private String userId;
    private String tel;
    private String name;
    private String department;

    @Builder
    public BoardResponseUserIdDto(String userId, String tel, String name, String department) {
        this.userId = userId;
        this.tel = tel;
        this.name = name;
        this.department = department;
    }

    public BoardResponseUserIdDto convertEntityToDto(UserEntity userEntity) {
        return BoardResponseUserIdDto.builder()
                .userId(userEntity.getUserid())
                .tel(userEntity.getTel())
                .name(userEntity.getName())
                .department(userEntity.getDepartment().getName())
                .build();
    }
}
