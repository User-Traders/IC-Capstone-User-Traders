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
    public List<BoardDto> findAll() { // 전체 목록 조회한 것
        List<BoardEntity> boardEntityList = boardRepository.findAll();

        List<BoardDto> results = boardEntityList.stream().map(boardEntity -> {
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
                .imageurl1(boardEntity.getImageurl1())
                .imageurl2(boardEntity.getImageurl2())
                .imageurl3(boardEntity.getImageurl3())
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
    public BoardDto findById(Integer id) { //PK가 id인 목록 1개 조회
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
        BoardEntity boardEntity = boardEntityWrapper.get();

        return this.convertEntityToDto(boardEntity);
    }

    @Transactional
    @Override
    public List<BoardDto> findAllSearch(String title) {
        List<BoardEntity> boardEntityList = boardRepository.findByTitleContaining(title);

        List<BoardDto> results = boardEntityList.stream().map(boardEntity -> {
            BoardDto boardDto = convertEntityToDto(boardEntity);
            return boardDto;
        }).collect(Collectors.toList());

        if (boardEntityList.isEmpty()) return results;

        return results;
    }

    @Transactional
    @Override
    public Integer save(BoardDto boardDto) { // 한 객체를 보드테이블에 저장
        BoardEntity boardEntity = boardDto.convertDtoToEntity();
        return boardRepository.save(boardEntity).getId();
    }

    @Transactional
    @Override
    public Integer updateById(BoardDto boardDto) {

        // 요청 받은 수정할 객체 정보를 건내받고 , 그 객체의 아이디를 뽑아서  수정전 정보를 wrapper에 담고
        // 수정 할 객체 정보를 수정전 객체 정보에 저장 ,  수정 끝
        //Optinal 클래스의 ifPresent 함수의 사용: 수정값에 null이 있는지 확인하는 if문을 줄이기 위함
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(boardDto.getId());
        boardEntityWrapper.ifPresent(boardEntity -> {
            boardEntity = boardDto.convertDtoToEntity();
            boardRepository.save(boardEntity);
        });

        return boardEntityWrapper.get().getId();

    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        boardRepository.deleteById(id);
    }


}
