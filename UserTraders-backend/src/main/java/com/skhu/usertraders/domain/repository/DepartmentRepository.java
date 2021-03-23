package com.skhu.usertraders.domain.repository;

import com.skhu.usertraders.domain.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {


}