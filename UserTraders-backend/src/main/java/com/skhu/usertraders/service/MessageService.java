package com.skhu.usertraders.service;


import com.skhu.usertraders.domain.entity.MessageEntity;
import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.domain.repository.MessageRepository;
import com.skhu.usertraders.dto.MessageDto;
import com.skhu.usertraders.dto.ReadEnum;
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

//    private MessageDto convertEntityToDto(MessageEntity messageEntity) {//엔티티 객체 변수를 디티오 객체 변수로 변환
//        return MessageDto.builder()
//                .id(messageEntity.getId())
//                .recvId(messageEntity.getRecvId())
//                .sentId(messageEntity.getSentId())
//                .title(messageEntity.getTitle())
//                .content(messageEntity.getContent())
//                .dateSent(messageEntity.getDateSent())
//                .dateRead(messageEntity.getDateRead())
//                .recvRead(messageEntity.getRecvRead())
//                .build();
//    }

    //메시지 작성 , 저장
    @Transactional
    public Integer addmessage(MessageDto messageDto) {
        LocalDateTime localDateTime = LocalDateTime.now();
        messageDto.setDateSent(localDateTime);
        ReadEnum readEnum = ReadEnum.valueOf("NO");
        System.out.println(readEnum);
        messageDto.setRecvRead(readEnum);
        System.out.println();



        MessageEntity messageEntity = messageDto.convertDtoToEntity();
        return messageRepository.save(messageEntity).getId();


    }

    //보낸 메시지 전체 목록
    @Transactional
    public List<MessageDto> sentmessage_list(UserEntity sentId) {

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
    public List<MessageDto> recvmessage_list(UserEntity recvId) {

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
    public MessageDto sentmessage_list_id(Integer id) {
        Optional<MessageEntity> messageEntityWrapper = messageRepository.findById(id);
        MessageEntity messageEntity = messageEntityWrapper.get();

        return new MessageDto().convertEntityToDto(messageEntity);

    }

    //받은 메시지 상세 정보 1개
    @Transactional
    public MessageDto recvmessage_list_id(Integer id) {

        Optional<MessageEntity> messageEntityWrapper = messageRepository.findById(id);
        MessageEntity messageEntity = messageEntityWrapper.get();

        LocalDateTime localDateTime = LocalDateTime.now();
        messageEntity.setDateRead(localDateTime);
        ReadEnum readEnum = ReadEnum.valueOf("YES");
        messageEntity.setRecvRead(readEnum);

        messageRepository.save(messageEntity);

        return new MessageDto().convertEntityToDto(messageEntity);
    }

    @Transactional
    public void deleteById(Integer id) {
        messageRepository.deleteById(id);
    }
}
