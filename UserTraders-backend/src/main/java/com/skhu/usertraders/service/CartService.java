package com.skhu.usertraders.service;

import com.skhu.usertraders.domain.entity.CartEntity;
import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.domain.repository.CartRepository;
import com.skhu.usertraders.dto.BoardDto;
import com.skhu.usertraders.dto.CartDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BoardService boardService;

    private CartDto convertEntityToDto(CartEntity cartEntity) { // 목록 조회시 테이블 엔티티 객체를 디티오 객체로 변환
        return CartDto.builder()
                .id(cartEntity.getId())
                .user(cartEntity.getUser())
                .board(cartEntity.getBoard())
                .build();
    }

    @Transactional
    public Integer cart_save(CartDto cartDto, UserEntity userEntity) {

        CartEntity cartEntity = cartDto.convertDtoToEntity();

        BoardDto boardDto = boardService.findById(cartDto.getBoard().getId());
        System.out.println(boardDto);
        System.out.println(userEntity.getId());
        System.out.println(boardDto.getUser().getId());

        // 장바구니를 저장했을때, 해당 게시물의 likecount가 1증가한다 .
        // 하지만 ,현재 로그인한 유저가 해당 게시물이 자신이 작성한 유저가 아니라면 증가한다.
        if (!boardDto.getUser().getId().equals(userEntity.getId())) {
            int likecount = cartDto.getBoard().getLikecount();
            likecount = likecount + 1;
            boardDto.setLikecount(likecount);
            boardService.save(boardDto);
        }


        return cartRepository.save(cartEntity).getId();
    }

    @Transactional
    public List<CartDto> find_cartlist(int id) { //해당 유저가 저장한 장바구니 목록만 출력
        List<CartEntity> cartEntityList = cartRepository.findByUserId(id);

        List<CartDto> results = cartEntityList.stream().map(cartEntity -> {
            CartDto cartDto = convertEntityToDto(cartEntity);
            return cartDto;
        }).collect(Collectors.toList());

        if (cartEntityList.isEmpty()) return results;

        return results;
    }

    @Transactional
    public void delete_cartlist(Integer id,Integer boardId){
        cartRepository.deleteById(id);

        BoardDto boardDto = boardService.findById(boardId);


        int likecount = boardDto.getLikecount();
        if (likecount >= 1) {
            likecount = likecount - 1;
            boardDto.setLikecount(likecount);
            boardService.save(boardDto);
        }
    }


}
