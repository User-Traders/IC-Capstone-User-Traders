
package com.skhu.usertraders.controller;

import com.skhu.usertraders.config.JwtTokenProvider;
import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.dto.user.UserDto;
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

@CrossOrigin(origins = "http://15.165.30.131:8090")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final CustomUserDetailService customUserDetailService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login") // 로그인
    public ResponseEntity login(@RequestBody Map<String, String> user) {
        return ResponseEntity.ok(customUserDetailService.login(user));
    }

    @PostMapping(value = "/signup") // 유저 회원 가입
    public ResponseEntity register(@RequestBody UserDto userDto) {
        customUserDetailService.save(userDto);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    @GetMapping(value = "/mypage") // 한 유저 상세 정보 단, 토큰 값이 있어야 가능
    public ResponseEntity mypage(@AuthenticationPrincipal UserEntity userEntity) {
        return ResponseEntity.ok(customUserDetailService.findById(userEntity));
    }

    @GetMapping(value = "/valid") // 토큰 값 유효성 검사를 하는 한개 api
    public boolean valid(@RequestParam("token") String token) {
        return jwtTokenProvider.validateToken(token);
    }

    @GetMapping(value = "/logout")// 로그 아웃 처리
    public ResponseEntity logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }

    @PutMapping(value = "/update/{id}") // 한 유저의 id 를 받아서 그 안에 들어 있는 정보 수정.
    public ResponseEntity update(@RequestBody @Validated UserDto userDto,
                                 @PathVariable("id") Integer id) {
        userDto.setId(id);
        customUserDetailService.updateById(userDto,id);
        return ResponseEntity.ok("내 정보를 수정 하였습니다.");
    }

    @DeleteMapping(value = "/delete/{id}") // 한 유저 삭제, 회원탈퇴
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        customUserDetailService.deleteById(id);
        return ResponseEntity.ok("회원이 탈퇴 되었습니다.");
    }
}
