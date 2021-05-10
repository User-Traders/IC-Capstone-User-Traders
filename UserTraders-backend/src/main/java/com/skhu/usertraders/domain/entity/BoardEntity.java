package com.skhu.usertraders.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
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

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "likecount", nullable = false)
    private int likecount;

    @Column(name = "viewcount", nullable = false)
    private int viewcount;

    @Column(name = "buycount", nullable = false)
    private int buycount;

    @Column(name = "cartcount", nullable = false)
    private int cartcount;

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
                       CategoryEntity category, UserEntity user, int likecount, int viewcount,
                       String imageurl1, String imageurl2, String imageurl3,
                       int buycount, int cartcount, Boolean status) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.price = price;
        this.category = category;
        this.user = user;
        this.imageurl1 = imageurl1;
        this.imageurl2 = imageurl2;
        this.imageurl3 = imageurl3;
        this.likecount = likecount;
        this.viewcount = viewcount;
        this.buycount = buycount;
        this.cartcount = cartcount;
        this.status = status;

    }

}
