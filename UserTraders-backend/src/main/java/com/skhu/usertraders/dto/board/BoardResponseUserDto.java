package com.skhu.usertraders.dto.board;

import com.skhu.usertraders.domain.entity.BoardEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardResponseUserDto {
    private String userId;

    @Builder
    public BoardResponseUserDto(String userId){
        this.userId = userId;
    }
    public BoardResponseUserDto convertEntityToDto(BoardEntity boardEntity) {
        return BoardResponseUserDto.builder()
                .userId(boardEntity.getUser().getUserid())
                .build();
    }
}
