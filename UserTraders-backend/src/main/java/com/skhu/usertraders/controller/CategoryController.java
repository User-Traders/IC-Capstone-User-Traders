package com.skhu.usertraders.controller;

import com.skhu.usertraders.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping(value = "/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/list/board"
    ) // 모든 유저의 학과 리스트 반환
    public ResponseEntity list_board() {

        return ResponseEntity.ok(categoryService.findAll());
    }

}


