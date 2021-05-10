package com.skhu.usertraders.service;

import com.skhu.usertraders.domain.entity.BoardEntity;
import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.domain.repository.BoardRepository;
import com.skhu.usertraders.dto.board.BoardDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
    private static final int DEFAULT_SIZE = 2;
    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    @Override
    public Integer save(BoardDto boardDto) { // 한 객체를 보드테이블에 저장
        BoardEntity boardEntity = boardDto.convertDtoToEntity();
        return boardRepository.save(boardEntity).getId();
    }

    @Transactional
    @Override
    public List<BoardDto> findAll() { // 전체 목록 조회한 것
        List<BoardEntity> boardEntityList = boardRepository.findAll();

        List<BoardDto> results = boardEntityList.stream().map(boardEntity -> {
            BoardDto boardDto = BoardDto.builder()
                    .build().convertEntityToDto(boardEntity);
            return boardDto;
        }).collect(Collectors.toList());
        System.out.println(results);

        return results;
    }

    @Transactional
    @Override
    public List<BoardDto> findAllInfinite(int limit) {//무한 스크롤
        Page<BoardEntity> page = boardRepository.findAll(PageRequest.of(limit - 1, 2, Sort.by(Sort.Direction.DESC, "createdDate")));
        List<BoardDto> results = page.stream().map(boardEntity -> {
            BoardDto boardDto = BoardDto.builder()
                    .build().convertEntityToDto(boardEntity);
            return boardDto;
        }).collect(Collectors.toList());
        return results;
    }

    @Transactional
    @Override
    public BoardDto findById(Integer id) { //PK가 id인 목록 1개 조회
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
        BoardEntity boardEntity = boardEntityWrapper.get();

        return BoardDto.builder().build().convertEntityToDto(boardEntity);
    }

    @Transactional
    @Override
    public List<BoardDto> findAllByUser(UserEntity userEntity) {
        List<BoardEntity> userBoardList = boardRepository.findAllByUser(userEntity);

        List<BoardDto> results = userBoardList.stream().map(boardEntity -> {
            BoardDto boardDto = BoardDto.builder()
                    .build().convertEntityToDto(boardEntity);
            return boardDto;
        }).collect(Collectors.toList());


        return results;
    }

    @Transactional
    @Override
    public List<BoardDto> findAllSearch(String title) {
        List<BoardEntity> boardEntityList = boardRepository.findByTitleContaining(title);

        List<BoardDto> results = boardEntityList.stream().map(boardEntity -> {
            BoardDto boardDto = BoardDto.builder()
                    .build().convertEntityToDto(boardEntity);
            return boardDto;
        }).collect(Collectors.toList());

        if (boardEntityList.isEmpty()) return results;

        return results;
    }

    @Transactional
    @Override
    public Integer updateById(BoardDto boardDto,Integer id) {
        BoardDto board = this.findById(id);

        // 요청 받은 수정할 객체 정보를 건내받고 , 그 객체의 아이디를 뽑아서  수정전 정보를 wrapper에 담고
        // 수정 할 객체 정보를 수정전 객체 정보에 저장 ,  수정 끝
        //Optinal 클래스의 ifPresent 함수의 사용: 수정값에 null이 있는지 확인하는 if문을 줄이기 위함
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(boardDto.getId());
        boardEntityWrapper.ifPresent(boardEntity -> {
            boardEntity = BoardEntity.builder()
                    .id(boardDto.getId())
                    .title(boardDto.getTitle())
                    .content(boardDto.getContent())
                    .price(boardDto.getPrice())
                    .imageurl1(board.getImageurl1())
                    .imageurl2(board.getImageurl2())
                    .imageurl3(board.getImageurl3())
                    .category(board.getCategory())
                    .user(board.getUser())
                    .status(board.getStatus()).build();
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
