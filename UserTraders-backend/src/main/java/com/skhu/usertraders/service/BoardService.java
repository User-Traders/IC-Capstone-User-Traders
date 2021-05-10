package com.skhu.usertraders.service;

import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.dto.board.BoardDto;

import javax.transaction.Transactional;
import java.util.List;


public interface BoardService { // 공통적인 부분은 가지되, 다형성을 가지기 위함.

    @Transactional
    List<BoardDto> findAll(); // 글 전체 조회

    @Transactional
    List<BoardDto> findAllInfinite(int limit);

    @Transactional
    BoardDto findById(Integer id);  //글 1개 조회

    @Transactional
    Integer save(BoardDto boardDto);  //글쓰기 저장

    @Transactional
    Integer updateById(BoardDto boardDto,Integer id);  //글 1개 수정

    @Transactional
    void deleteById(Integer id);  // 글 1개 삭제

    @Transactional
    List<BoardDto> findAllSearch(String title);

    @Transactional
    List<BoardDto> findAllByUser(UserEntity userEntity);
}
