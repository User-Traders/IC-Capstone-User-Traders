package com.skhu.usertraders.dto.boardlike;

import com.skhu.usertraders.domain.entity.BoardEntity;
import com.skhu.usertraders.domain.entity.LikeEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LikeStatusResponseDto {
    private Boolean status;

    public static LikeStatusResponseDto from(LikeEntity boardlike) {
        return new LikeStatusResponseDto(boardlike.getStatus());
    }
    @Builder
    public LikeStatusResponseDto(Boolean status) {
        this.status = status;
    }

    public LikeStatusResponseDto convertEntityToDto(LikeEntity likeEntity) { //board 테이블안의 전체 칼럼 조회를 위한, 엔티티 객체를 디티오 객체 로 변환
        return LikeStatusResponseDto.builder()
                .status(likeEntity.getStatus())
                .build();
    }
}
