package com.skhu.usertraders.service;

import com.skhu.usertraders.domain.entity.CartEntity;
import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.domain.repository.CartRepository;
import com.skhu.usertraders.dto.board.BoardDto;
import com.skhu.usertraders.dto.board.BoardResponseUserDto;
import com.skhu.usertraders.dto.cart.CartRequestDto;
import com.skhu.usertraders.dto.cart.CartResponseDto;
import com.skhu.usertraders.exception.board.ApiIllegalArgumentException;
import com.skhu.usertraders.exception.board.ApiNullPointerException;
import com.skhu.usertraders.exception.board.ApiUserNullPointerException;
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

    // 장바구니 저장 로직에는 1. 게시물을 작성한 자신이 장바구니를 저장할 수 없어야한다.
    //2.똑같은 장바구니는 저장 할 수 없어야한다.
    @Transactional
    public Integer save(CartRequestDto cartDto, UserEntity user) {

        if(user == null){
            throw new ApiUserNullPointerException("로그인 되지 않았습니다. 로그인 해주세요.");
        }
        BoardResponseUserDto boardResponseUserDto =
                boardService.findUserIdWhereBoardId(cartDto.getBoardId());
        if (user.getUserid().equals(boardResponseUserDto.getUserId())) {
            throw new ApiIllegalArgumentException("나의 게시물은 장바구니에 담을 수 없습니다.");
        }

        List<CartEntity> cartEntity = cartRepository.findAllByBoard_IdAndUser(cartDto.getBoardId(), user);
        if (cartEntity.size() >= 1) {
            throw new ApiIllegalArgumentException("중복된 게시물은 장바구니에 담을 수 없습니다.");
        }

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
    public List<CartResponseDto> findByUserId(UserEntity user) { //해당 유저가 저장한 장바구니 목록만 출력
        if (user == null){throw new ApiUserNullPointerException("유저 정보가 없습니다. 다시 로그인해주세요.");
        }
        List<CartEntity> carts = cartRepository.findByUserId(user.getId());
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