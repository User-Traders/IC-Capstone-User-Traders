package com.skhu.usertraders.dto.user;

import com.skhu.usertraders.domain.entity.DepartmentEntity;
import com.skhu.usertraders.domain.entity.UserEntity;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    // 등록날짜
    private LocalDateTime createdDate;
    // 수정날짜
    private LocalDateTime modifiedDate;
    // 이름
    private String name;
    //권한 테이블
    private List<String> roles = new ArrayList<>();
    // 학과번호
    private DepartmentEntity department;

    public UserEntity convertDtoToEntity() { //회원 정보 추가를 위한 엔티티 - 디티오 변환 ,그러나 안쓰고 직접 빌드 하여 사용했다.
        return UserEntity.builder()
                .id(id)
                .userid(userid)
                .password(password)
                .tel(tel)
                .department(department)
                .name(name)
                .roles(roles)
                .build();
    }
    public UserDto UserEntityToDto(UserEntity userEntity) {//나중에 쓸 일 있을것 같아서 유저정보들 객체에 담아놨음
        return UserDto.builder()
                .id(userEntity.getId())
                .userid(userEntity.getUserid())
                .password(userEntity.getPassword())
                .tel(userEntity.getTel())
                .department(userEntity.getDepartment())
                .createdDate(userEntity.getCreatedDate())
                .modifiedDate(userEntity.getModifiedDate())
                .name(userEntity.getName())
                .roles(userEntity.getRoles())
                .build();
    }
    @Builder
    public UserDto(Integer id, String userid, String password, String tel, DepartmentEntity department, LocalDateTime createdDate,
                   LocalDateTime modifiedDate, String name, List<String> roles) {

        this.id = id;
        this.userid = userid;
        this.password = password;
        this.tel = tel;
        this.department = department;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.name = name;
        this.roles = roles;
    }
}
