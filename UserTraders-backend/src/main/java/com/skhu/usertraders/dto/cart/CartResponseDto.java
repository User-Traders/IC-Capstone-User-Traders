package com.skhu.usertraders.dto.cart;

import com.skhu.usertraders.domain.entity.BoardEntity;
import com.skhu.usertraders.domain.entity.CartEntity;
import com.skhu.usertraders.domain.entity.UserEntity;
import lombok.*;

@Getter
@AllArgsConstructor
public class CartResponseDto {
    private Integer id;
    private BoardEntity board;

    public static CartResponseDto from(CartEntity cart) {
        return new CartResponseDto(cart.getId(), cart.getBoard());
    }
}