package com.skhu.usertraders.controller;

import com.skhu.usertraders.dto.BoardDto;
import com.skhu.usertraders.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(value = "/boards")
public class BoardController {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private BoardService boardService;
    private WebApplicationContext request;

    @GetMapping(value = "/list") // 모든 게시물 리스트 반환
    public ResponseEntity list() {

        return ResponseEntity.ok(boardService.findAll());
    }

    @GetMapping(value = "/listInfinte") // 모든 게시물 리스트 반환
    public ResponseEntity list(@RequestParam(value = "limit", defaultValue = "1") int limit) {
        log.info("zz" + limit);
        return ResponseEntity.ok(boardService.findAllInfinite(limit));
    }


    @GetMapping(value = "/list/{id}") // 한 게시물의 id 안에 들어 있는 정보를 반환
    public ResponseEntity list(@PathVariable("id") Integer id) { //@PathVariable :url 파라미터 값 id를 인자로 받음
        log.info("id:{}", id);
        return ResponseEntity.ok(boardService.findById(id));
    }

    @GetMapping(value = "/list/search") //검색기능
    public ResponseEntity search(@RequestParam(value = "keyword") String keyword) {
        return ResponseEntity.ok(boardService.findAllSearch(keyword));
    }


    @PostMapping(value = "/register") // 한 게시물 저장
    public ResponseEntity register(BoardDto boardDto, List<MultipartFile> files) { //@RequestBody :HTTP 요청 몸체를 자바 객체로 변환
        log.info("user");
        String baseDir = "C:\\SKHU-project\\IC-Capstone-User-Traders\\UserTraders-frontend\\src\\assets\\images\\";

        String[] fileName = new String[3];
        if (files != null) {
            try {
                for (int i = 0; i < files.size(); i++) {
                    fileName[i] = files.get(i).getOriginalFilename();
                    files.get(i).transferTo(new File(baseDir + files.get(i).getOriginalFilename()));
                }
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        boardDto.setImageurl1(fileName[0]);
        boardDto.setImageurl2(fileName[1]);
        boardDto.setImageurl3(fileName[2]);
        boardService.save(boardDto);
        return ResponseEntity.ok(true);
    }


    @PutMapping(value = "/list/{id}") // 한 게시물의 id 를 받아서 그 안에 들어있는 정보 수정.
    public ResponseEntity update(@RequestBody @Validated BoardDto boardDto,
                                 @PathVariable("id") Integer id) {
        boardDto.setId(id);
        boardService.updateById(boardDto);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping(value = "/list/{id}") // 한 게시물 삭제
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        boardService.deleteById(id);
        return ResponseEntity.ok(true);
    }


}
