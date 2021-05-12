package com.skhu.usertraders.dto.cart;

import com.skhu.usertraders.domain.entity.BoardEntity;
import com.skhu.usertraders.domain.entity.CartEntity;
import com.skhu.usertraders.domain.entity.CategoryEntity;
import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.dto.board.BoardDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CartResponseDto {
    private Integer id;
    private BoardEntity board;

    @Builder
    public CartResponseDto(Integer id,BoardEntity board) {
        this.id = id;
        this.board = board;
    }

    public static CartResponseDto from(CartEntity cart) {
        return new CartResponseDto(cart.getId(), cart.getBoard());
    }
    public CartResponseDto convertEntityToDto(CartEntity cartEntity) { //board 테이블안의 전체 칼럼 조회를 위한, 엔티티 객체를 디티오 객체 로 변환
        return CartResponseDto.builder()
                .id(cartEntity.getId())
                .board(cartEntity.getBoard())
                .build();
    }
}