package com.skhu.usertraders.domain.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "user")
public class UserEntity extends TimeEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userid")
    private String userid;

    @Column(name = "password")
    private String password;

    @Column(name = "tel")
    private String tel;

    @Column(name = "department_id")
    private Integer departmentId;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "name")
    private String name;


    @Builder
    public UserEntity(Integer id , String userid , String password , String tel , Integer departmentId ,
                      Boolean enabled , String name){

        this.id = id;
        this.userid = userid;
        this.password = password;
        this.tel = tel;
        this.departmentId = departmentId;
        this.enabled = enabled;
        this.name = name;
    }


}
