package com.skhu.usertraders.dto;


import com.skhu.usertraders.domain.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {

    // 회원 고유 번호
    private Integer id;

    // 아이디
    private String userid;

    // 비밀번호
    private String password;

    // 전화번호
    private String tel;

    // 학과번호
    private Integer departmentId;

    // 등록날짜
    private LocalDateTime createdDate;

    // 수정날짜
    private LocalDateTime modifiedDate;


    // 이름
    private String name;

    public UserEntity convertEntityToEntity() { //회원 정보 추가를 위한 엔티티 - 디티오 변환
        return UserEntity.builder()
                .id(id)
                .userid(userid)
                .password(password)
                .tel(tel)
                .departmentId(departmentId)
                .name(name)
                .build();
    }

    @Builder
    public UserDto(Integer id, String userid, String password, String tel, Integer departmentId, LocalDateTime createdDate,
                   LocalDateTime modifiedDate, String name) {

        this.id = id;
        this.userid = userid;
        this.password = password;
        this.tel = tel;
        this.departmentId = departmentId;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.name = name;
    }
}
