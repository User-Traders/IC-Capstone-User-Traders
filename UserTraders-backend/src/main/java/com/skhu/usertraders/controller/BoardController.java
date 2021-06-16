package com.skhu.usertraders.controller;

import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.dto.board.BoardDto;
import com.skhu.usertraders.exception.board.ApiIllegalArgumentException;
import com.skhu.usertraders.exception.board.ApiNullPointerException;
import com.skhu.usertraders.exception.board.ApiRequestException;
import com.skhu.usertraders.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping(value = "/request/exception")
    public String requestException() throws ApiRequestException{
        throw new ApiRequestException("400 에러!!, 요청값을 다시 확인해주세요");
    }

    @GetMapping(value = "/request/illegal/exception")
    public String requestIllegalStateException() throws ApiIllegalArgumentException {
        throw new ApiIllegalArgumentException("400에러!! 그중에 IllegalStateException!!");
    }

    @GetMapping(value = "/request/null/exception")
    public String requestNullPointerException() throws ApiNullPointerException {
        throw new ApiNullPointerException("400 에러!!, 그중에 NullPointerException");
    }

    @GetMapping(value = "/access/exception")
    public String accessDeniedException() throws AccessDeniedException {
        throw new AccessDeniedException("401 에러!!, 요청값을 다시 확인해주세요");
    }

    @GetMapping(value = "/internal/exception")
    public String internalServerError() throws Exception {
        throw new Exception("500 에러!!, 요청값을 다시 확인해주세요");
    }

    @GetMapping(value = "/list") // 모든 게시물 리스트 반환,조회
    public ResponseEntity<List<BoardDto>> list() {
        return ResponseEntity.ok().body(boardService.findAll());
    }

    @GetMapping(value = "/listInfinte") // 모든 게시물 리스트 반환,조회
    public ResponseEntity list(@RequestParam(value = "limit", defaultValue = "1") int limit) {
        return ResponseEntity.ok().body(boardService.findAllInfinite(limit));
    }

    @GetMapping(value = "/list/{id}") // 한 게시물의 id 안에 들어 있는 정보를 반환,조회
    public ResponseEntity list(@PathVariable("id") Integer id) { //@PathVariable :url 파라미터 값 id를 인자로 받음
        return ResponseEntity.ok(boardService.findById(id));
    }

    @GetMapping(value = "/keword/search") //키워드로 검색기능
    public ResponseEntity kewordSearch(@RequestParam String keyword) {
        return ResponseEntity.ok(boardService.findByTitleContaining(keyword));
    }

    @GetMapping(value = "/category/search/{id}")
    public ResponseEntity categorySearch(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(boardService.findAllByCategoryContaining(id));
    }

    @GetMapping(value = "/list/user/board")// 어떤 한 유저가 가지고 있는 게시물 조회
    public ResponseEntity findAllByUser(@AuthenticationPrincipal UserEntity userEntity) {
        return ResponseEntity.ok(boardService.findAllByUser(userEntity));
    }

    @PostMapping(value = "/register") // 한 게시물 저장
    public ResponseEntity register(BoardDto boardDto, List<MultipartFile> files,@AuthenticationPrincipal UserEntity user) {//@RequestBody :HTTP 요청 몸체를 자바 객체로 변환
        boardService.save(boardDto, files,user);
        return ResponseEntity.ok(boardDto.getId()+"번 게시물이 저장되었습니다.");
    }

    @PutMapping(value = "/list/{id}") // 한 게시물의 id 를 받아서 그 안에 들어 있는 게시물 정보 수정.
    public ResponseEntity update(@RequestBody @Validated BoardDto boardDto,
                                 @PathVariable("id") Integer id) {
        boardDto.setId(id);
        boardService.updateById(boardDto, id);
        return ResponseEntity.ok(id+"번 게시물이 수정되었습니다.");

    }

    @DeleteMapping(value = "/list/{id}") // 한 게시물 삭제
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        boardService.deleteById(id);
        return ResponseEntity.ok(id+"번 게시물이 삭제되었습니다.");
    }

    @GetMapping(value = "/list/{id}/userId") // 한 게시물의 id 안에 그 게시물을 작성한 유저의 userid(이메일)
    public ResponseEntity listUser(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(boardService.findUserIdWhereBoardId(id));
    }

    @GetMapping(value = "/list/userId")
    public ResponseEntity listUserId(@RequestParam(value = "userId") String userId){
        return ResponseEntity.ok(boardService.findAllByUserId(userId));
    }

}
