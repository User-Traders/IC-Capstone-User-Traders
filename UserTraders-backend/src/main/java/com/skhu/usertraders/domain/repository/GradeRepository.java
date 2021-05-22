package com.skhu.usertraders.domain.repository;

import com.skhu.usertraders.domain.entity.GradeEntity;
import com.skhu.usertraders.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<GradeEntity, Integer> {

    List<GradeEntity> findByRecvId(UserEntity user);

}

