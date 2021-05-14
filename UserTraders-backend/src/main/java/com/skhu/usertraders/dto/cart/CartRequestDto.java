package com.skhu.usertraders.dto.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartRequestDto {
    private Integer boardId;

    public CartRequestDto(Integer boardId) {
        this.boardId = boardId;
    }
}
