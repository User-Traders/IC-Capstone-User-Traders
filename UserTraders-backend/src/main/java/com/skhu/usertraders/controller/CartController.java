package com.skhu.usertraders.controller;

import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.dto.CartDto;
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
public class CartController {

    @Autowired
    private CartService cartService;


    @PostMapping(value = "/register") // 장바구니 저장
    public ResponseEntity cart_register(@RequestBody @Validated CartDto cartDto , @AuthenticationPrincipal UserEntity userEntity) {
        System.out.println(cartDto);
        cartService.cart_save(cartDto,userEntity);

        return ResponseEntity.ok(true);
    }

    @GetMapping(value = "/list") //한 유저 장바구니 목록 단, 토큰값이 있어야 가능
    public ResponseEntity cart_list(@AuthenticationPrincipal UserEntity userEntity) {
        return ResponseEntity.ok(cartService.find_cartlist(userEntity.getId()));
    }

    @DeleteMapping(value = "/list/{id}/{boardId}") // 장바구니 삭제
    public ResponseEntity cart_delete(@PathVariable("id") Integer id,
                                      @PathVariable("boardId") Integer boardId) {
        cartService.delete_cartlist(id,boardId);

        return ResponseEntity.ok(true);
    }
}
