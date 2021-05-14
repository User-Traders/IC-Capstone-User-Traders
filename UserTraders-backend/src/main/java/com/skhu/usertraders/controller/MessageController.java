package com.skhu.usertraders.controller;


import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.dto.message.MessageDto;
import com.skhu.usertraders.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/messages")
public class MessageController {
    @Autowired
    MessageService messageService;

    @PostMapping(value = "/send") // 메시지 저장
    public ResponseEntity register(@RequestBody MessageDto messageDto, @AuthenticationPrincipal UserEntity userEntity) {
        messageDto.setSentId(userEntity);
        messageService.save(messageDto);
        return ResponseEntity.ok("메시지가 정상적으로 보내졌습니다.");
    }
    //보낸 쪽지함 목록
    @GetMapping(value = "/list/sent")
    public ResponseEntity listSent(@RequestParam(value = "sentId") UserEntity sentId) {
        return ResponseEntity.ok(messageService.findAllBySentId(sentId));
    }
    //받은 쪽지함 목록
    @GetMapping(value = "/list/recv")
    public ResponseEntity listRecv(@RequestParam(value = "recvId") UserEntity recvId) {
        return ResponseEntity.ok(messageService.findAllByRecvId(recvId));
    }
    //보낸 쪽지함 메시지 상세 정보
    @GetMapping(value = "/list/sent/{id}")
    public ResponseEntity listSentId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(messageService.findBySentId(id));
    }
    //받은 쪽지함 메시지 상세 정보
    @GetMapping(value = "/list/recv/{id}")
    public ResponseEntity listRecvId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(messageService.findByRecvId(id));
    }
    //쪽지 삭제
    @DeleteMapping(value = "/list/{id}") // 한 게시물 삭제
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        messageService.deleteById(id);
        return ResponseEntity.ok(true);
    }
    //받은 쪽지함 메시지 개수
    @GetMapping(value = "/list/recv/count")
    public ResponseEntity listRecvCount(@RequestParam(value = "recvId") UserEntity recvId) {
        return ResponseEntity.ok(messageService.listRecvCount(recvId));
    }
}
