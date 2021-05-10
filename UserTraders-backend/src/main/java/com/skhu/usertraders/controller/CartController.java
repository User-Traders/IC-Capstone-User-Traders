package com.skhu.usertraders.controller;

import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.dto.cart.CartRequestDto;
import com.skhu.usertraders.dto.cart.CartResponseDto;
import com.skhu.usertraders.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/carts")
@CrossOrigin(origins = "http://localhost:8080")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping(value = "/register") // 장바구니 저장
    public ResponseEntity cartRegister(@RequestBody CartRequestDto cartDto, @AuthenticationPrincipal UserEntity userEntity) {
        cartService.cartsave(cartDto, userEntity);
        return ResponseEntity.ok("장바구니 등록 완료");
    }

    @GetMapping(value = "/list") //한 유저 장바구니 목록 단, 토큰값이 있어야 가능
    public ResponseEntity cartList(@AuthenticationPrincipal UserEntity userEntity) {
        return ResponseEntity.ok(cartService.findCartlist(userEntity.getId()));
    }

    @DeleteMapping(value = "/list/{id}/{boardId}") // 장바구니 삭제
    public ResponseEntity cartDelete(@AuthenticationPrincipal UserEntity userEntity, @PathVariable("id") Integer id) {
        cartService.deletecartlist(id, userEntity.getId());
        return ResponseEntity.ok(true);
    }
}
