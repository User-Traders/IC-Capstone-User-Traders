package com.skhu.usertraders.controller;

import com.skhu.usertraders.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
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
    public ResponseEntity list_user() {

        return ResponseEntity.ok(departmentService.findAll());
    }


}
