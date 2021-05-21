package com.skhu.usertraders.dto.boardlike;

import com.skhu.usertraders.domain.entity.BoardEntity;
import com.skhu.usertraders.domain.entity.LikeEntity;
import com.skhu.usertraders.domain.entity.UserEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LikeResponseUserDto {
    private Integer id;
    private UserEntity user;
    private Boolean status;

    public static LikeResponseUserDto from(LikeEntity boardlike) {
        return new LikeResponseUserDto(boardlike.getId(), boardlike.getUser(), boardlike.getStatus());
    }

    @Builder
    public LikeResponseUserDto(Integer id, UserEntity user, Boolean status) {
        this.id = id;
        this.user = user;
        this.status = status;
    }

    public LikeResponseUserDto convertEntityToDto(LikeEntity likeEntity) { //board 테이블안의 전체 칼럼 조회를 위한, 엔티티 객체를 디티오 객체 로 변환
        return LikeResponseUserDto.builder()
                .id(likeEntity.getId())
                .user(likeEntity.getUser())
                .status(likeEntity.getStatus())
                .build();
    }
}