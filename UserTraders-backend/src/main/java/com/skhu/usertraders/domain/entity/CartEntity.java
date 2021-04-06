package com.skhu.usertraders.domain.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "board_cart")
public class CartEntity {

    // 장바구니 고유번호
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    // 회원 고유 번호
    @ManyToOne
    @JoinColumn(name = "user_id")  // 유저 아이디
    private UserEntity user;

    // 게시물 고유번호
    @ManyToOne
    @JoinColumn(name = "board_id") // 보드 아이디, joinColum의 name값과 테이블 명을 꼭 같게 해줘!!!!이틀새지말고
    private BoardEntity board;


    @Builder
    public CartEntity(Integer id, UserEntity user, BoardEntity board) {
        this.id = id;
        this.user = user;
        this.board = board;
    }
}
