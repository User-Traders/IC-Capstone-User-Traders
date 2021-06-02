package com.skhu.usertraders.controller;

import com.skhu.usertraders.service.DepartmentService;
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
@RequestMapping(value = "/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping(value = "/list"
    ) // 모든 학과 리스트 반환
    public ResponseEntity list() {

        return ResponseEntity.ok(departmentService.findDepartment());
    }

    @GetMapping(value = "/list/user"
    ) // 모든 유저의 학과 리스트 반환
    public ResponseEntity listUser() {

        return ResponseEntity.ok(departmentService.findAll());
    }


}
