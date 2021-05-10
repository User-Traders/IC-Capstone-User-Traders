package com.skhu.usertraders.service;

import com.skhu.usertraders.domain.entity.CartEntity;
import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.domain.repository.CartRepository;
import com.skhu.usertraders.dto.BoardDto;
import com.skhu.usertraders.dto.cart.CartRequestDto;
import com.skhu.usertraders.dto.cart.CartResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BoardService boardService;
//    @Transactional
//    public Integer cart_save(CartResponseDto cartDto, UserEntity userEntity) {
//
//        CartEntity cartEntity = cartDto.convertDtoToEntity();
//
//        BoardDto boardDto = boardService.findById(cartDto.getBoard().getId());
//        System.out.println(boardDto);
//        System.out.println(userEntity.getId());
//        System.out.println(boardDto.getUser().getId());
//
//        // 장바구니를 저장했을때, 해당 게시물의 likecount가 1증가한다 .
//        // 하지만 ,현재 로그인한 유저가 해당 게시물이 자신이 작성한 유저가 아니라면 증가한다.
//        if (!boardDto.getUser().getId().equals(userEntity.getId())) {
//            int likecount = cartDto.getBoard().getLikecount();
//            likecount = likecount + 1;
//            boardDto.setLikecount(likecount);
//            boardService.save(boardDto);
//        }
//
//
//        return cartRepository.save(cartEntity).getId();
//    }

    @Transactional
    public CartResponseDto cart_save(CartRequestDto cartDto, UserEntity user) {
        BoardDto board = boardService.findById(cartDto.getBoardId());
        CartEntity cart = CartEntity.builder()
                .board(board.convertDtoToEntity())
                .user(user)
                .build();
        return CartResponseDto.from(cartRepository.save(cart));
    }

//        BoardDto boardDto = boardService.findById(cartDto.getBoard().getId());
//        System.out.println(boardDto);
//        System.out.println(userEntity.getId());
//        System.out.println(boardDto.getUser().getId());
//
//        // 장바구니를 저장했을때, 해당 게시물의 cartcount가 1증가한다 .
//        // 하지만 ,현재 로그인한 유저가 해당 게시물이 자신이 작성한 유저가 아니라면 증가한다.
//        if (!boardDto.getUser().getId().equals(userEntity.getId())) {
//            int cartcount = cartDto.getBoard().getCartcount();
//            cartcount = cartcount + 1;
//            boardDto.setCartcount(cartcount);
//            boardService.save(boardDto);
//        }
//
//
//        return cartRepository.save(cartEntity).getId();
//    }




    
//    @Transactional
//    public List<CartResponseDto> find_cartlist(int id) { //해당 유저가 저장한 장바구니 목록만 출력
//        List<CartEntity> cartEntityList = cartRepository.findByUserId(id);
//
//        List<CartResponseDto> results = cartEntityList.stream().map(cartEntity -> {
//            CartResponseDto cartDto = convertEntityToDto(cartEntity);
//            return cartDto;
//        }).collect(Collectors.toList());
//
//        if (cartEntityList.isEmpty()) return results;
//
//        return results;
//    }

    @Transactional
    public List<CartResponseDto> findCartlist(Integer id) { //해당 유저가 저장한 장바구니 목록만 출력
        List<CartEntity> carts = cartRepository.findByUserId(id);
        return carts.stream().map(CartResponseDto::from).collect(Collectors.toList());
    }

    @Transactional
    public void delete_cartlist(Integer id, Integer boardId) {
        cartRepository.deleteById(id);
        BoardDto boardDto = boardService.findById(boardId);

        int cartcount = boardDto.getCartcount();
        if (cartcount >= 1) {
            cartcount = cartcount - 1;
            boardDto.setCartcount(cartcount);

            boardService.save(boardDto);
        }
    }
}
