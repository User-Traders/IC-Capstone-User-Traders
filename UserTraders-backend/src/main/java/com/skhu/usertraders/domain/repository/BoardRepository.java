package com.skhu.usertraders.domain.repository;

import com.skhu.usertraders.domain.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

    Optional<BoardEntity> findById(Integer id);
    List<BoardEntity> findByTitleContaining(String title);
}
