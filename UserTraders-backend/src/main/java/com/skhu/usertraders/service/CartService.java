package com.skhu.usertraders.service;

import com.skhu.usertraders.domain.entity.CartEntity;
import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.domain.repository.CartRepository;
import com.skhu.usertraders.dto.board.BoardDto;
import com.skhu.usertraders.dto.cart.CartRequestDto;
import com.skhu.usertraders.dto.cart.CartResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BoardService boardService;

    @Transactional
    public Integer save(CartRequestDto cartDto, UserEntity user) {
        BoardDto board = boardService.findById(cartDto.getBoardId());
        CartEntity cart = CartEntity.builder()
                .board(board.convertDtoToEntity())
                .user(user)
                .build();

        if (!board.getUser().getId().equals(user.getId())) {
            int cartcount = board.getCartcount();

            cartcount = cartcount + 1;
            board.setCartcount(cartcount);
            boardService.save(board);
        }
        return cartRepository.save(cart).getId();
    }
    @Transactional
    public List<CartResponseDto> findByUserId(Integer id) { //해당 유저가 저장한 장바구니 목록만 출력
        List<CartEntity> carts = cartRepository.findByUserId(id);
        return carts.stream().map(CartResponseDto::from).collect(Collectors.toList());
    }

    @Transactional
    public CartResponseDto findByCartId(Integer id) { //장바구니 고유 번호로 한 목록 찾기
        Optional<CartEntity> cartEntityWrapper = cartRepository.findById(id);
        CartEntity cartEntity = cartEntityWrapper.get();
        return CartResponseDto.builder().build().convertEntityToDto(cartEntity);
    }

    @Transactional
    public void deleteById(Integer id) {
        BoardDto boardDto = boardService.findById(this.findByCartId(id).getBoard().getId());
        int cartcount = boardDto.getCartcount();
        if (cartcount >= 1) {
            cartcount = cartcount - 1;
            boardDto.setCartcount(cartcount);
            boardService.save(boardDto);
        }
        cartRepository.deleteById(id);
    }
}