package com.skhu.usertraders.dto.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class MessageRequestDto {

    private String recvId;

    private String title;

    private String content;

}
