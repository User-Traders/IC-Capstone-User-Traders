package com.skhu.usertraders.dto.boardlike;

import com.skhu.usertraders.domain.entity.BoardEntity;
import com.skhu.usertraders.domain.entity.LikeEntity;
import com.skhu.usertraders.domain.entity.CartEntity;
import com.skhu.usertraders.dto.cart.CartResponseDto;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LikeResponseDto {
    private Integer id;
    private BoardEntity board;
    private Boolean status;

    public static LikeResponseDto from(LikeEntity boardlike) {
        return new LikeResponseDto(boardlike.getId(), boardlike.getBoard(), boardlike.getStatus());
    }

    @Builder
    public LikeResponseDto(Integer id, BoardEntity board, Boolean status) {
        this.id = id;
        this.board = board;
        this.status = status;
    }

    public static CartResponseDto from(CartEntity cart) {
        return new CartResponseDto(cart.getId(), cart.getBoard());
    }

    public LikeResponseDto convertEntityToDto(LikeEntity likeEntity) { //board 테이블안의 전체 칼럼 조회를 위한, 엔티티 객체를 디티오 객체 로 변환
        return LikeResponseDto.builder()
                .id(likeEntity.getId())
                .board(likeEntity.getBoard())
                .status(likeEntity.getStatus())
                .build();
    }
}