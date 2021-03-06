package com.skhu.usertraders.domain.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "board")
public class BoardEntity extends TimeEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "price")
    private String price;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "likecount")
    private Integer likecount;

    @Column(name = "viewcount")
    private Integer viewcount;

    @Column(name = "buycount")
    private Integer buycount;

    @Column(name = "status")
    private Boolean status;

    @Builder
    public BoardEntity(Integer id, String title, String content, String price,
                    Integer categoryId, Integer userId, Integer likecount, Integer viewcount,
                    Integer buycount, Boolean status) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.price = price;
        this.categoryId = categoryId;
        this.userId = userId;
        this.likecount = likecount;
        this.viewcount = viewcount;
        this.buycount = buycount;
        this.status = status;

    }

}
