package com.skhu.usertraders.dto.boardlike;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LikeRequestDto {
    private Integer boardId;

    public LikeRequestDto(Integer boardId) {
        this.boardId = boardId;
    }
}
