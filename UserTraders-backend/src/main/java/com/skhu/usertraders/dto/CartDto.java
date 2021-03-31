package com.skhu.usertraders.dto;

import com.skhu.usertraders.domain.entity.BoardEntity;
import com.skhu.usertraders.domain.entity.CartEntity;
import com.skhu.usertraders.domain.entity.UserEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CartDto {

    // 장바구니 번호
    private Integer id;

    // 유저고유번호
    private UserEntity user;

    // 게시물 고유번호
    private BoardEntity board;


    public CartEntity convertDtoToEntity() { //저장을 위해서 장바구니 저장시 , cartDto를 cartEntity 로 변환
        CartEntity cartEntity = CartEntity.builder()
                .id(id)
                .user(user)
                .board(board)
                .build();

        return cartEntity;
    }


    @Builder
    public CartDto(Integer id, UserEntity user, BoardEntity board) {
        this.id = id;
        this.user = user;
        this.board = board;
    }
}