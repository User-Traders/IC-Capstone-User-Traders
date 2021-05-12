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
    public Integer save(CartRequestDto cartDto, UserEntity user) {
        //지금 장바구니 요청 dto 안에는 보드의 게시물 번호만  있음, 그리고 유저 엔티티 객체 user는 토큰값으로 받은 객체고
        //번호만 받아서 밑에서 그번호에 해당되는 게시물의 객체를 board에 담았음
        BoardDto board = boardService.findById(cartDto.getBoardId());
        CartEntity cart = CartEntity.builder()
                .board(board.convertDtoToEntity())
                .user(user)
                .build();
        //그래서 해당 보드객체랑 해당 유저객체를 카트 엔티티 카트라는 객체에 빌더를 써서 담았고,
        // 마지막에 저장된값의 리턴값은 cartRepository.save(cart) 해주면 되는거아닌가? 응답 디티오가 필요한것인가?

        // 장바구니를 저장했을때, 해당 게시물의 cartcount가 1증가한다 .
        // 하지만 ,현재 로그인한 유저가 해당 게시물이 자신이 작성한 유저가 아니라면 증가한다.
        if (!board.getUser().getId().equals(user.getId())) {
            int cartcount = board.getCartcount();

            cartcount = cartcount + 1;
            board.setCartcount(cartcount);
            boardService.save(board);
        }
        // 그리하여 리턴값을 정수형으로 하였고 1개의 카트의 고유번호를 리턴해줌.
        return cartRepository.save(cart).getId();
    }


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
    public void deleteById(Integer id, UserEntity user) {
        BoardDto boardDto = boardService.findById(this.findByCartId(id).getBoard().getId());
        //장바구니 고유번호로 찾은 보드게시물에 장바구니 삭제시 -1 해줘야함
        int cartcount = boardDto.getCartcount();
        if (cartcount >= 1) {
            cartcount = cartcount - 1;
            boardDto.setCartcount(cartcount);
            boardService.save(boardDto);
        }
        cartRepository.deleteByIdAndUser(id, user);
    }
}
