package com.skhu.usertraders.domain.entity;

import com.skhu.usertraders.dto.ReadEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user_notes")
public class MessageEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "recv_id")
    private UserEntity recvId;

    @ManyToOne
    @JoinColumn(name = "sent_id")
    private UserEntity sentId;


    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;


    @Column(name = "date_sent")
    private LocalDateTime dateSent;

    @Column(name = "date_read")
    private LocalDateTime dateRead;

    @Column(name = "recv_read")
    @Enumerated(EnumType.STRING)
    private ReadEnum recvRead;


    @Builder
    public MessageEntity(Integer id, UserEntity recvId, UserEntity sentId, String title, String content,
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
