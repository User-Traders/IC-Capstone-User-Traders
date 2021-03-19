package com.skhu.usertraders.domain.entity;


import lombok.*;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "board")
public class BoardEntity extends TimeEntity {

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

    @Column(name = "imageurl1")
    private String imageurl1;

    @Column(name = "imageurl2")
    private String imageurl2;

    @Column(name = "imageurl3")
    private String imageurl3;

    @Builder
    public BoardEntity(Integer id, String title, String content, String price,
                       Integer categoryId, Integer userId, Integer likecount, Integer viewcount,
                       String imageurl1,String imageurl2,String imageurl3,
                       Integer buycount, Boolean status) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.price = price;
        this.categoryId = categoryId;
        this.imageurl1 = imageurl1;
        this.imageurl2 = imageurl2;
        this.imageurl3 = imageurl3;
        this.userId = userId;
        this.likecount = likecount;
        this.viewcount = viewcount;
        this.buycount = buycount;
        this.status = status;

    }

}
