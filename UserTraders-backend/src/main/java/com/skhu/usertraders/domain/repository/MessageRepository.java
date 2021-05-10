package com.skhu.usertraders.domain.repository;

import com.skhu.usertraders.domain.entity.MessageEntity;
import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.dto.board.ReadEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {

    List<MessageEntity> findAllBySentId(UserEntity sentId);

    List<MessageEntity> findAllByRecvId(UserEntity recvId);

    List<MessageEntity> findByRecvIdEqualsAndRecvReadEquals(UserEntity recvId, ReadEnum recvRead);

}
