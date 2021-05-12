package com.skhu.usertraders.service;

import com.skhu.usertraders.domain.entity.MessageEntity;
import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.domain.repository.MessageRepository;
import com.skhu.usertraders.dto.message.MessageDto;
import com.skhu.usertraders.dto.board.ReadEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    //메시지 작성 , 저장
    @Transactional
    public Integer save(MessageDto messageDto) {
        LocalDateTime localDateTime = LocalDateTime.now();
        messageDto.setDateSent(localDateTime);
        ReadEnum readEnum = ReadEnum.valueOf("NO");
        messageDto.setRecvRead(readEnum);
        MessageEntity messageEntity = messageDto.convertDtoToEntity();
        return messageRepository.save(messageEntity).getId();
    }

    //보낸 메시지 전체 목록
    @Transactional
    public List<MessageDto> findAllBySentId(UserEntity sentId) {
        List<MessageEntity> messageEntityList = messageRepository.findAllBySentId(sentId);
        System.out.println(messageEntityList);
        List<MessageDto> results = messageEntityList.stream().map(messageEntity -> {
            MessageDto messageDto = new MessageDto().
                    convertEntityToDto(messageEntity);
            return messageDto;
        }).collect(Collectors.toList());
        return results;
    }

    //받은 메시지 전체 목록
    @Transactional
    public List<MessageDto> findAllByRecvId(UserEntity recvId) {
        List<MessageEntity> messageEntityList = messageRepository.findAllByRecvId(recvId);
        List<MessageDto> results = messageEntityList.stream().map(messageEntity -> {
            MessageDto messageDto = new MessageDto().
                    convertEntityToDto(messageEntity);
            return messageDto;
        }).collect(Collectors.toList());
        return results;
    }

    //보낸 메시지 상세 정보 1개
    @Transactional
    public MessageDto findBySentId(Integer id) {
        Optional<MessageEntity> messageEntityWrapper = messageRepository.findById(id);
        MessageEntity messageEntity = messageEntityWrapper.get();
        return new MessageDto().convertEntityToDto(messageEntity);
    }

    //받은 메시지 상세 정보 1개
    @Transactional
    public MessageDto findByRecvId(Integer id) {
        Optional<MessageEntity> messageEntityWrapper = messageRepository.findById(id);
        MessageEntity messageEntity = messageEntityWrapper.get();

        LocalDateTime localDateTime = LocalDateTime.now();
        messageEntity.setDateRead(localDateTime);
        ReadEnum readEnum = ReadEnum.valueOf("YES");
        messageEntity.setRecvRead(readEnum);
        messageRepository.save(messageEntity);
        return new MessageDto().convertEntityToDto(messageEntity);
    }

    //메시지 1개 삭제
    @Transactional
    public void deleteById(Integer id) {
        messageRepository.deleteById(id);
    }

    //받은 메시지 목록 개수
    @Transactional
    public Integer listRecvCount(UserEntity recvId) {
        List<MessageEntity> messageEntityList =  messageRepository.findByRecvIdEqualsAndRecvReadEquals(recvId,ReadEnum.NO);
        List<MessageDto> results = messageEntityList.stream().map(messageEntity -> {
            MessageDto messageDto = new MessageDto().
                    convertEntityToDto(messageEntity);
            return messageDto;
        }).collect(Collectors.toList());

        int a = results.size();
        return a;
    }
}
