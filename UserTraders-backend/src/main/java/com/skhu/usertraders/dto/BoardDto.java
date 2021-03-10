package com.skhu.usertraders.dto;


import com.skhu.usertraders.domain.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {

    // 게시물 고유번호
    private Integer id;

    // 제품 제목
    private String title;

    // 제품 내용
    private String content;

    // 제품 가격
    private String price;

    // 제품 카테고리
    private Integer categoryId;

    // 제품 작성자 고유 번호
    private Integer userId;

    // 등록날짜
    private LocalDateTime createdDate;

    // 수정날짜
    private LocalDateTime modifiedDate;

    // 찜수
    private Integer likecount;

    // 조회수
    private Integer viewcount;

    // 구매 평점
    private Integer buycount;

    // 판매 상태
    private Boolean status;


//    public BoardEntity toEntity() { //글쓰기 저장을 위한 엔티티
//        BoardEntity boardEntity = BoardEntity.builder()
//                .id(id)
//                .title(title)
//                .content(content)
//                .price(price)
//                .categoryId(categoryId)
//                .userId(userId)
//                .createdDate(createdDate)
//                .modifiedDate(modifiedDate)
//                .likecount(likecount)
//                .viewcount(viewcount)
//                .buycount(buycount)
//                .status(status)
//                .build();
//        return boardEntity;
//    }

    @Builder
    public BoardDto(Integer id, String title, String content, String price,
                    Integer categoryId, Integer userId, LocalDateTime createdDate,
                    LocalDateTime modifiedDate, Integer likecount, Integer viewcount,
                    Integer buycount, Boolean status) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.price = price;
        this.categoryId = categoryId;
        this.userId = userId;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.likecount = likecount;
        this.viewcount = viewcount;
        this.buycount = buycount;
        this.status = status;

    }

}

