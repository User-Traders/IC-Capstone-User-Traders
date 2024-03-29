package com.skhu.usertraders.controller;

import com.skhu.usertraders.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://15.165.30.131:8080")
@RequestMapping(value = "/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/list/board")
    public ResponseEntity list() {
        return ResponseEntity.ok(categoryService.findAll());
    }

}


