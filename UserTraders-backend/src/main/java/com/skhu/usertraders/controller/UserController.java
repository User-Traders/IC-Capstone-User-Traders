
package com.skhu.usertraders.controller;

import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.dto.UserDto;
import com.skhu.usertraders.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final CustomUserDetailService customUserDetailService;


    // 로그인
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Map<String, String> user) {

        return ResponseEntity.ok(customUserDetailService.login(user));

    }

    @PostMapping(value = "/signup") // 유저 회원가입
    public ResponseEntity register(@RequestBody UserDto userDto) {
        customUserDetailService.save(userDto);
        return ResponseEntity.ok(true);
    }

    @GetMapping(value = "/mypage") // 한 유저 상세정보 단, 토큰값이 있어야 가능
    public ResponseEntity mypage(@AuthenticationPrincipal UserEntity userEntity) {

        return ResponseEntity.ok(customUserDetailService.find(userEntity.getId()));
    }

    @GetMapping(value = "/logout")//로그아웃 처리
    public ResponseEntity logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.ok(true);
    }

    @PutMapping(value = "/update/{id}") // 한 게시물의 id 를 받아서 그 안에 들어있는 정보 수정.
    public ResponseEntity update(@RequestBody @Validated UserDto userDto,
                                 @PathVariable("id") Integer id) {
        userDto.setId(id);
        customUserDetailService.updateById(userDto);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping(value = "/delete/{id}") // 한 게시물 삭제
    public ResponseEntity delete(@PathVariable("id") Integer id) {

        customUserDetailService.deleteById(id);
        return ResponseEntity.ok(true);
    }
}
