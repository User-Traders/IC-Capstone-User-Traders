package com.skhu.usertraders.controller;

import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.dto.cart.CartRequestDto;
import com.skhu.usertraders.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/carts")
@CrossOrigin(origins = "http://localhost:8080")
public class CartController {

    @Autowired
    private CartService cartService;


    @PostMapping(value = "/register")
    public ResponseEntity cartRegister(@RequestBody CartRequestDto cartDto,@AuthenticationPrincipal UserEntity userEntity ) {
        cartService.save(cartDto, userEntity);
        return ResponseEntity.ok(cartDto.getBoardId()+"번 게시물 장바구니 등록이 완료되었습니다.");
    }

    @GetMapping(value = "/list")
    public ResponseEntity cartList(@AuthenticationPrincipal UserEntity userEntity) {
        return ResponseEntity.ok(cartService.findByUserId(userEntity.getId()));
    }

    @DeleteMapping("/list/delete/{id}")
    public ResponseEntity cartDelete(@PathVariable("id") Integer id) {
        cartService.deleteById(id);
        return ResponseEntity.ok().body("장바구니 삭제가 완료되었습니다.");
    }
}
