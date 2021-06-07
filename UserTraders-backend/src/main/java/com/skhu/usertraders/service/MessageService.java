package com.skhu.usertraders.service;

import com.skhu.usertraders.domain.entity.MessageEntity;
import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.domain.repository.MessageRepository;
import com.skhu.usertraders.dto.board.ReadEnum;
import com.skhu.usertraders.dto.message.MessageAllResponseDto;
import com.skhu.usertraders.dto.message.MessageRequestDto;
import com.skhu.usertraders.dto.user.UserDto;
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

    @Autowired
    CustomUserDetailService userService;

    //메시지 작성 , 저장
    @Transactional
    public Integer save(MessageRequestDto messageDto, UserEntity userEntity) {
        UserDto recvuser = userService.findById(Integer.parseInt(messageDto.getRecvId()));
        ReadEnum readEnum = ReadEnum.valueOf("NO");

        MessageEntity messageEntity = MessageEntity.builder()
                .recvId(recvuser.convertDtoToEntity())
                .sentId(userEntity)
                .title(messageDto.getTitle())
                .content(messageDto.getContent())
                .dateSent(LocalDateTime.now())
                .dateRead(null)
                .recvRead(readEnum)
                .build();
        return messageRepository.save(messageEntity).getId();
    }

    //보낸 메시지 전체 목록
    @Transactional
    public List<MessageAllResponseDto> findAllBySentId(UserEntity sentId) {
        List<MessageEntity> messageEntityList = messageRepository.findAllBySentId(sentId);
        System.out.println(messageEntityList);
        List<MessageAllResponseDto> results = messageEntityList.stream().map(messageEntity -> {
            MessageAllResponseDto messageAllResponseDto = new MessageAllResponseDto().
                    convertEntityToDto(messageEntity);
            return messageAllResponseDto;
        }).collect(Collectors.toList());
        return results;
    }

    //받은 메시지 전체 목록
    @Transactional
    public List<MessageAllResponseDto> findAllByRecvId(UserEntity recvId) {
        List<MessageEntity> messageEntityList = messageRepository.findAllByRecvId(recvId);
        List<MessageAllResponseDto> results = messageEntityList.stream().map(messageEntity -> {
            MessageAllResponseDto messageAllResponseDto = new MessageAllResponseDto().
                    convertEntityToDto(messageEntity);
            return messageAllResponseDto;
        }).collect(Collectors.toList());
        return results;
    }

    //보낸 메시지 상세 정보 1개
    @Transactional
    public MessageAllResponseDto findBySentId(Integer id) {
        Optional<MessageEntity> messageEntityWrapper = messageRepository.findById(id);
        MessageEntity messageEntity = messageEntityWrapper.get();
        return new MessageAllResponseDto().convertEntityToDto(messageEntity);
    }

    //받은 메시지 상세 정보 1개
    @Transactional
    public MessageAllResponseDto findByRecvId(Integer id) {
        Optional<MessageEntity> messageEntityWrapper = messageRepository.findById(id);
        MessageEntity messageEntity = messageEntityWrapper.get();

        LocalDateTime localDateTime = LocalDateTime.now();
        messageEntity.setDateRead(localDateTime);
        ReadEnum readEnum = ReadEnum.valueOf("YES");
        messageEntity.setRecvRead(readEnum);
        messageRepository.save(messageEntity);
        return new MessageAllResponseDto().convertEntityToDto(messageEntity);
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
        List<MessageAllResponseDto> results = messageEntityList.stream().map(messageEntity -> {
            MessageAllResponseDto messageAllResponseDto = new MessageAllResponseDto().
                    convertEntityToDto(messageEntity);
            return messageAllResponseDto;
        }).collect(Collectors.toList());

        int a = results.size();
        System.out.println(a);
        return a;
    }
}