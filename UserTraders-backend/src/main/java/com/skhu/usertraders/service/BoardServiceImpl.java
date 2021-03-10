package com.skhu.usertraders.service;

import com.skhu.usertraders.domain.entity.BoardEntity;
import com.skhu.usertraders.domain.repository.BoardRepository;
import com.skhu.usertraders.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {


    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    @Override
    public List<BoardDto> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll(); // 전체 목록 조회한 것

        List<BoardDto> results = boardEntityList.stream().map(boardEntity->{
           BoardDto boardDto = convertEntityToDto(boardEntity);
           return boardDto;
        }).collect(Collectors.toList());
        System.out.println(results);

        return results;
    }

    private BoardDto convertEntityToDto(BoardEntity boardEntity) { //엔티티 객체 변수를 디티오 객체 변수로 변환
        return BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .price(boardEntity.getPrice())
                .categoryId(boardEntity.getCategoryId())
                .userId(boardEntity.getUserId())
                .createdDate(boardEntity.getCreatedDate())
                .modifiedDate(boardEntity.getModifiedDate())
                .likecount(boardEntity.getLikecount())
                .viewcount(boardEntity.getViewcount())
                .buycount(boardEntity.getBuycount())
                .status(boardEntity.getStatus())
                .build();
    }


    @Transactional
    @Override
    public BoardDto findById(Integer id) {
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
        BoardEntity boardEntity = boardEntityWrapper.get();

        return this.convertEntityToDto(boardEntity);
    }

    @Transactional
    @Override
    public BoardEntity save(BoardDto boardDto) {
        return null;
    }

    @Transactional
    @Override
    public void updateById(Integer id) {

    }

    @Transactional
    @Override
    public void deleteById(Integer id) {

    }


}
