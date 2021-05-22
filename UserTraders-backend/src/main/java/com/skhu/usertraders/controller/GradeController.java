package com.skhu.usertraders.controller;


import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.dto.grade.GradeRequestDto;
import com.skhu.usertraders.dto.message.MessageRequestDto;
import com.skhu.usertraders.service.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/grades")
public class GradeController {
    @Autowired
    GradeService gradeService;


    @PostMapping(value = "/register") // 평점 저장하기
    public ResponseEntity register(@RequestBody GradeRequestDto requestDto,@AuthenticationPrincipal UserEntity user) {
        gradeService.save(requestDto,user);
        return ResponseEntity.ok("평점이 저장 되었습니다.");
    }

    @GetMapping(value = "/list")
    public ResponseEntity list() {
        return ResponseEntity.ok(gradeService.findAll());
    }

    @GetMapping(value = "/list/avg/recvId")
    public ResponseEntity listRecvIdGradeAvg(@RequestParam(value = "recvId") String recvId) {
        return ResponseEntity.ok(gradeService.findGradeAvgByRecvId(recvId));
    }
}
