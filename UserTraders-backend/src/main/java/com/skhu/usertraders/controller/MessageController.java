package com.skhu.usertraders.controller;


import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.dto.message.MessageRequestDto;
import com.skhu.usertraders.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://15.165.30.131:8090")
@RequestMapping(value = "/messages")
public class MessageController {
    @Autowired
    MessageService messageService;

    @PostMapping(value = "/send") // 메시지 저장,보내기
    public ResponseEntity register(@RequestBody MessageRequestDto messageDto, @AuthenticationPrincipal UserEntity userEntity) {
        messageService.save(messageDto,userEntity);
        return ResponseEntity.ok("메시지가 정상적으로 보내졌습니다.");
    }
    //보낸 쪽지함 목록, 여기로 토큰값 보내면 됨
    @GetMapping(value = "/list/user/sent")
    public ResponseEntity listSent(@AuthenticationPrincipal UserEntity sentId) {
        return ResponseEntity.ok(messageService.findAllBySentId(sentId));
    }
    //받은 쪽지함 목록 , 여기로 토큰값 보내면 됨
    @GetMapping(value = "/list/user/recv")
    public ResponseEntity listRecv(@AuthenticationPrincipal UserEntity recvId) {
        return ResponseEntity.ok(messageService.findAllByRecvId(recvId));
    }
    //보낸 쪽지함 메시지 상세 정보
    @GetMapping(value = "/list/user/sent/{id}")
    public ResponseEntity listSentId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(messageService.findBySentId(id));
    }
    //받은 쪽지함 메시지 상세 정보
    @GetMapping(value = "/list/user/recv/{id}")
    public ResponseEntity listRecvId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(messageService.findByRecvId(id));
    }
    //쪽지 삭제
    @DeleteMapping(value = "/list/{id}") // 한 게시물 삭제
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        messageService.deleteById(id);
        return ResponseEntity.ok("게시물이 삭제 되었습니다.");
    }
    //받은 쪽지함 메시지 개수, 그 중 수신 확인 NO 칼럼
    @GetMapping(value = "/list/user/recv/count")
    public ResponseEntity listRecvCount(@AuthenticationPrincipal UserEntity recvId) {
        return ResponseEntity.ok(messageService.listRecvCount(recvId));
    }
}
