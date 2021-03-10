package com.skhu.usertraders.service;

import com.skhu.usertraders.domain.entity.BoardEntity;
import com.skhu.usertraders.dto.BoardDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface BoardService {

    @Transactional
    List<BoardDto> findAll(); // 글 전체 조회

    @Transactional
    BoardDto findById(Integer id);  //글 1개 조회

    @Transactional
    BoardEntity save(BoardDto boardDto);  //글쓰기 저장

    @Transactional
    void updateById(Integer id);  //글 1개 수정

    @Transactional
    void deleteById(Integer id);  // 글 1개 삭제
}
