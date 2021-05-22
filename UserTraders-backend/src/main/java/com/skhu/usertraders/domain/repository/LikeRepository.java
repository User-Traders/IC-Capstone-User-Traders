package com.skhu.usertraders.domain.repository;

import com.skhu.usertraders.domain.entity.LikeEntity;
import com.skhu.usertraders.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Integer> {
    List<LikeEntity> findByUserId(Integer userid); //좋아요를 저장한 유저의 것인 특정한 칼럼만 출력 시키기 위한 쿼리 메소드

    void deleteByIdAndUser(Integer id, UserEntity user);

    Optional<LikeEntity> findById(Integer id);

    @Query("select b from LikeEntity b  where b.board.id = :boardId and b.user= :user")
    List<LikeEntity> findByStatus(@Param("boardId") Integer boardId, @Param("user") UserEntity user);

    List<LikeEntity> findByBoard_Id(Integer BoardId);
}