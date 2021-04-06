package com.skhu.usertraders.controller;


import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.dto.MessageDto;
import com.skhu.usertraders.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/messages")
public class MessageController {
    @Autowired
    MessageService messageService;

    @PostMapping(value = "/send") // 메시지 저장
    public ResponseEntity add(@RequestBody MessageDto messageDto, @AuthenticationPrincipal UserEntity userEntity) {

        messageDto.setSentId(userEntity);
        messageService.addmessage(messageDto);

        return ResponseEntity.ok(true);
    }

    //보낸 쪽지함 목록
    @GetMapping(value = "/list/sent")
    public ResponseEntity sent_list(@RequestParam(value = "sentId") UserEntity sentId) {

        System.out.println(sentId);

        return ResponseEntity.ok(messageService.sentmessage_list(sentId));
    }


    //받은 쪽지함 목록
    @GetMapping(value = "/list/recv")
    public ResponseEntity recv_list(@RequestParam(value = "recvId") UserEntity recvId) {
        System.out.println(recvId);
        return ResponseEntity.ok(messageService.recvmessage_list(recvId));
    }


    //보낸 쪽지함 메시지 상세 정보
    @GetMapping(value = "/list/sent/{id}")
    public ResponseEntity sent_list(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(messageService.sentmessage_list_id(id));
    }

    //받은 쪽지함 메시지 상세 정보
    @GetMapping(value = "/list/recv/{id}")
    public ResponseEntity recv_list(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(messageService.recvmessage_list_id(id));
    }

    //쪽지 삭제
    @DeleteMapping(value = "/list/{id}") // 한 게시물 삭제
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        messageService.deleteById(id);
        return ResponseEntity.ok(true);
    }

}
