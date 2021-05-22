package com.skhu.usertraders.dto.boardlike;

import com.skhu.usertraders.domain.entity.BoardEntity;
import com.skhu.usertraders.domain.entity.LikeEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LikeResponseBoardDto {
    private Integer id;
    private BoardEntity board;
    private Boolean status;

    public static LikeResponseBoardDto from(LikeEntity boardlike) {
        return new LikeResponseBoardDto(boardlike.getId(), boardlike.getBoard(), boardlike.getStatus());
    }

    @Builder
    public LikeResponseBoardDto(Integer id, BoardEntity board, Boolean status) {
        this.id = id;
        this.board = board;
        this.status = status;
    }

    public LikeResponseBoardDto convertEntityToDto(LikeEntity likeEntity) { //board 테이블안의 전체 칼럼 조회를 위한, 엔티티 객체를 디티오 객체 로 변환
        return LikeResponseBoardDto.builder()
                .id(likeEntity.getId())
                .board(likeEntity.getBoard())
                .status(likeEntity.getStatus())
                .build();
    }
}