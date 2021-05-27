package com.skhu.usertraders.service;

import com.skhu.usertraders.domain.entity.BoardEntity;
import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.domain.repository.BoardRepository;
import com.skhu.usertraders.domain.repository.CategoryRepository;
import com.skhu.usertraders.dto.board.BoardDto;
import com.skhu.usertraders.dto.board.BoardResponseUserDto;
import com.skhu.usertraders.dto.board.BoardResponseUserIdDto;
import com.skhu.usertraders.dto.user.UserDto;
import com.skhu.usertraders.exception.board.ApiIllegalArgumentException;
import com.skhu.usertraders.exception.board.ApiNullPointerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private CustomUserDetailService userService;

    @Transactional
    @Override

    public Integer save(BoardDto boardDto, List<MultipartFile> files, UserEntity user) { // 한 객체를 보드 테이블에 저장, 파일까지 저장
        String baseDir = "C:\\SKHU-project\\IC-Capstone-User-Traders\\UserTraders-frontend\\src\\assets\\images\\";
        String[] fileName = new String[3];
        if (files != null) {
            try {
                for (int i = 0; i < files.size(); i++) {
                    fileName[i] = files.get(i).getOriginalFilename();
                    files.get(i).transferTo(new File(baseDir + files.get(i).getOriginalFilename()));
                }
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        boardDto.setImageurl1(fileName[0]);
        boardDto.setImageurl2(fileName[1]);
        boardDto.setImageurl3(fileName[2]);
        boardDto.setUser(user);

        BoardEntity boardEntity = boardDto.convertDtoToEntity();
        return boardRepository.save(boardEntity).getId();
    }

    @Transactional
    @Override
    public Integer save(BoardDto boardDto) { // 한 객체를 보드 테이블에 저장
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

        return results;
    }
    @Override
    public List<BoardDto> findAllByCategoryContaining(Integer id) {
        List<BoardEntity> boardEntityList = boardRepository.findAllByCategory_Id(id);
        List<BoardDto> results = boardEntityList.stream().map(boardEntity -> {
            BoardDto boardDto = BoardDto.builder()
                    .build().convertEntityToDto(boardEntity);
            return boardDto;
        }).collect(Collectors.toList());

        return results;
    }
    @Override
    public List<BoardDto> findByTitleContaining(String keyword) {
        List<BoardEntity> boardEntityList = boardRepository.findByTitleContaining(keyword);
        List<BoardDto> results = boardEntityList.stream().map(boardEntity -> {
            BoardDto boardDto = BoardDto.builder()
                    .build().convertEntityToDto(boardEntity);
            return boardDto;
        }).collect(Collectors.toList());

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
        BoardEntity boardEntity = boardRepository.findById(id)
                .orElseThrow(() -> new ApiIllegalArgumentException("해당되는 게시물 번호 " + id + " 값의 상세정보가 없습니다."));
        BoardDto board = BoardDto.builder().build().convertEntityToDto(boardEntity);

        int viewcount = board.getViewcount();
        viewcount = viewcount + 1;
        board.setViewcount(viewcount);
        this.save(board);
        return board;
    }

    @Transactional
    @Override
    public List<BoardDto> findAllByUser(UserEntity userEntity) {
        if (userEntity == null) {
            throw new ApiNullPointerException("유저 정보가 없습니다");
        }
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
    public BoardResponseUserDto findUserIdWhereBoardId(Integer id) {
        BoardEntity boardEntity = boardRepository.findById(id)
                .orElseThrow(() -> new ApiIllegalArgumentException("해당되는 게시물 번호 " + id + " 값의 상세정보가 없습니다."));

        return BoardResponseUserDto.builder().build().convertEntityToDto(boardEntity);
    }

    @Override
    public BoardResponseUserIdDto findAllByUserId(String userId) {
        UserDto userDto = userService.findByUserId(userId);
        return BoardResponseUserIdDto.builder().build().convertEntityToDto(userDto.convertDtoToEntity());
    }

    @Transactional
    @Override
    public Integer updateById(BoardDto boardDto, Integer id) {
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
        if (id == null) {
            throw new ApiNullPointerException("삭제하려는 게시물" + id + "아이디가 없습니다.");
        }
        boardRepository.deleteById(id);
    }


}
