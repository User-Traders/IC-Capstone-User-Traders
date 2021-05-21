package com.skhu.usertraders.dto.message;

import com.skhu.usertraders.domain.entity.MessageEntity;
import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.dto.board.ReadEnum;
import lombok.*;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MessageDto {

    // 쪽지 고유번호
    private Integer id;
    // 받는이 아이디
    private UserEntity recvId;
    // 보낸이 아이디
    private UserEntity sentId;
    // 쪽지 제목
    private String title;
    // 쪽지 내용
    private String content;
    // 쪽지 보낸 날짜
    private LocalDateTime dateSent;
    // 쪽지 읽은 날짜
    private LocalDateTime dateRead;
    // 받는이 확인여부
    @Enumerated(EnumType.STRING)
    private ReadEnum recvRead;

    public MessageDto(String s) {}

    public MessageEntity convertDtoToEntity() { //메시지 저장을 위한 엔티티
        return MessageEntity.builder()
                .id(id)
                .recvId(recvId)
                .sentId(sentId)
                .title(title)
                .content(content)
                .dateSent(dateSent)
                .dateRead(dateRead)
                .recvRead(recvRead)
                .build();
    }
    public MessageDto convertEntityToDto(MessageEntity messageEntity) {//엔티티 객체 변수를 디티오 객체 변수로 변환 해서 조회.
        return MessageDto.builder()
                .id(messageEntity.getId())
                .recvId(messageEntity.getRecvId())
                .sentId(messageEntity.getSentId())
                .title(messageEntity.getTitle())
                .content(messageEntity.getContent())
                .dateSent(messageEntity.getDateSent())
                .dateRead(messageEntity.getDateRead())
                .recvRead(messageEntity.getRecvRead())
                .build();
    }
    @Builder
    public MessageDto(Integer id, UserEntity recvId, UserEntity sentId, String title, String content,
                      LocalDateTime dateSent, LocalDateTime dateRead, ReadEnum recvRead) {
        this.id = id;
        this.recvId = recvId;
        this.sentId = sentId;
        this.title = title;
        this.content = content;
        this.dateSent = dateSent;
        this.dateRead = dateRead;
        this.recvRead = recvRead;
    }
}



