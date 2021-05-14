package com.skhu.usertraders.domain.repository;

import com.skhu.usertraders.domain.entity.CartEntity;
import com.skhu.usertraders.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {
    List<CartEntity> findByUserId(Integer userid); //장바구니를 저장한 유저의 것인 특정한 칼럼만 출력 시키기 위한 쿼리 메소드

    Optional<CartEntity> findById(Integer id);
}

