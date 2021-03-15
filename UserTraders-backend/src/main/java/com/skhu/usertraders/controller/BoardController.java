package com.skhu.usertraders.controller;

import com.skhu.usertraders.dto.BoardDto;
import com.skhu.usertraders.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@AllArgsConstructor
@RequestMapping(value = "/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping(value = "/list"
    ) // 모든 게시물 리스트 반환
    public ResponseEntity list() {

        return ResponseEntity.ok(boardService.findAll());
    }

    @GetMapping(value = "/list/{id}") // 한 게시물의 id 안에 들어 있는 정보를 반환
    public ResponseEntity list(@PathVariable("id") Integer id) { //@PathVariable :url 파라미터 값 id를 인자로 받음

        return ResponseEntity.ok(boardService.findById(id));
    }

    @GetMapping(value = "/list/search") //검색기능
    public ResponseEntity search(@RequestParam(value = "keyword") String keyword ){
        return ResponseEntity.ok(boardService.findAllSearch(keyword));
    }


    @PostMapping(value = "/register") // 한 게시물 저장
    public ResponseEntity register(@RequestBody @Validated BoardDto boardDto) { //@RequestBody :HTTP 요청 몸체를 자바 객체로 변환
        boardService.save(boardDto);
        return ResponseEntity.ok(null);
    }


    @PutMapping(value = "/list/{id}") // 한 게시물의 id 를 받아서 그 안에 들어있는 정보 수정.
    public ResponseEntity update(@RequestBody @Validated BoardDto boardDto,
                                 @PathVariable("id") Integer id) {
        boardDto.setId(id);
        boardService.updateById(boardDto);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping(value = "/list/{id}") // 한 게시물 삭제
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        boardService.deleteById(id);
        return ResponseEntity.ok(null);
    }


}
