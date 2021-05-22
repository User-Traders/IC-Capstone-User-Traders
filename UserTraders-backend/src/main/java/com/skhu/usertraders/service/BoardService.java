package com.skhu.usertraders.service;

import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.dto.board.BoardDto;
import com.skhu.usertraders.dto.board.BoardResponseUserDto;
import com.skhu.usertraders.dto.board.BoardResponseUserIdDto;
import org.springframework.web.multipart.MultipartFile;

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
    Integer save(BoardDto boardDto, List<MultipartFile> files, UserEntity user);  //글쓰기 맨처음, 파일 까지 저장

    @Transactional
    Integer save(BoardDto boardDto);  //글쓰기 저장

    @Transactional
    Integer updateById(BoardDto boardDto, Integer id);  //글 1개 수정

    @Transactional
    void deleteById(Integer id);  // 글 1개 삭제

    @Transactional
    List<BoardDto> findByTitleContaining(String keyword);

    @Transactional
    List<BoardDto> findAllByCategoryContaining(Integer id);

    @Transactional
    List<BoardDto> findAllByUser(UserEntity userEntity);

    @Transactional
    BoardResponseUserDto findUserIdWhereBoardId(Integer id);

    @Transactional
    BoardResponseUserIdDto findAllByUserId(String userId);
}
