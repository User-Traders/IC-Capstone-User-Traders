package com.skhu.usertraders.domain.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Table(name = "board_like")
public class LikeEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardEntity board;

    @Column(name = "status")
    private Boolean status;

    @Builder
    public LikeEntity(BoardEntity board, UserEntity user, Boolean status) {
        this.board = board;
        this.user = user;
        this.status = status;
    }
}

