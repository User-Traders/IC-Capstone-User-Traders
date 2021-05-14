package com.skhu.usertraders.controller;

import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.dto.boardlike.LikeRequestDto;
import com.skhu.usertraders.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/boards/likes")
@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping(value = "/register") // 좋아요 저장(클릭)
    public ResponseEntity register(@RequestBody LikeRequestDto likeDto, @AuthenticationPrincipal UserEntity userEntity) {
        likeService.save(likeDto, userEntity);
        return ResponseEntity.ok("이 게시물을 좋아요 하였습니다.");
    }

    @GetMapping(value = "/list") //한 유저가 내가 좋아요 한 게시물 객체들
    public ResponseEntity list(@AuthenticationPrincipal UserEntity userEntity) {
        return ResponseEntity.ok(likeService.findByUserId(userEntity.getId()));
    }

    @GetMapping(value = "/list/status/{boardId}") // 한 유저가 한 게시물에 대한 좋아요 상태 // 이거 디티오 새로 파야함 다나와서
    public ResponseEntity listStatus(@AuthenticationPrincipal UserEntity userEntity, @PathVariable("boardId") Integer boardId) {
        return ResponseEntity.ok(likeService.findByStatus(boardId, userEntity));
    }

    @DeleteMapping(value = "/list/{id}") // 해당 좋아요 취소
    public ResponseEntity delete(@AuthenticationPrincipal UserEntity userEntity, @PathVariable("id") Integer id) {
        likeService.deleteById(id, userEntity);
        return ResponseEntity.ok(true);
    }
}
