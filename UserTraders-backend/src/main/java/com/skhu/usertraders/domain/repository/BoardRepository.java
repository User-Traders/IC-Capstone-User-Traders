package com.skhu.usertraders.domain.repository;

import com.skhu.usertraders.domain.entity.BoardEntity;
import com.skhu.usertraders.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

    List<BoardEntity> findByTitleContaining(String title);

    List<BoardEntity> findAllByUser(UserEntity userEntity);

    List<BoardEntity> findAllByCategory_Id(Integer id);
}
