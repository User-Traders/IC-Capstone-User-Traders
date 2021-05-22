package com.skhu.usertraders.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "user_grades")
public class GradeEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "recv_id")
    private UserEntity recvId;

    @ManyToOne
    @JoinColumn(name = "sent_id")
    private UserEntity sentId;

    @Column(name = "grade")
    private Integer grade;

    @Builder
    public GradeEntity(Integer id ,UserEntity recvId , UserEntity sentId , Integer grade){
        this.id = id;
        this.recvId = recvId;
        this.sentId = sentId;
        this.grade = grade;
    }




}
