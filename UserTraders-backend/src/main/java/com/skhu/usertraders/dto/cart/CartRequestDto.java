package com.skhu.usertraders.dto.cart;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CartRequestDto {
    private Integer boardId;

    public CartRequestDto(Integer boardId) {
        this.boardId = boardId;
    }
}
