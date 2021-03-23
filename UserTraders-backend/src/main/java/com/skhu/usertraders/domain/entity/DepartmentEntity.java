package com.skhu.usertraders.domain.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "department")
public class DepartmentEntity {

    @Id
    @Column(name = "id")
    // 학과번호
    private Integer id;


    @Column(name = "name")
    // 학과명
    private String name;

    @Builder
    public DepartmentEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

}
