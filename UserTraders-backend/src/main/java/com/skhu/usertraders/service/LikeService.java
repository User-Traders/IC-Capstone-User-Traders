package com.skhu.usertraders.service;

import com.skhu.usertraders.domain.entity.LikeEntity;
import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.domain.repository.LikeRepository;
import com.skhu.usertraders.dto.board.BoardDto;
import com.skhu.usertraders.dto.boardlike.LikeRequestDto;
import com.skhu.usertraders.dto.boardlike.LikeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    private final BoardService boardService;

    @Transactional
    public Integer save(LikeRequestDto likeDto, UserEntity user) {
        BoardDto board = boardService.findById(likeDto.getBoardId());
        LikeEntity likeEntity = LikeEntity.builder()
                .board(board.convertDtoToEntity())
                .user(user)
                .status(true)
                .build();
        // 게시물에 좋아요를 누르면, 해당 게시물의 likecount가 1증가한다 .
        // 하지만 ,현재 로그인한 유저가 해당 게시물이 자신이 작성한 유저가 아니라면 증가한다.
        if (!board.getUser().getId().equals(user.getId())) {
            int likecount = board.getLikecount();
            likecount = likecount + 1;
            board.setLikecount(likecount);
            boardService.save(board);
        }
        //  리턴값을 정수형으로 하였고 1개의 좋아요 테이블의 고유번호를 리턴해줌.
        return likeRepository.save(likeEntity).getId();
    }

    @Transactional
    public List<LikeResponseDto> findByUserId(Integer userId) { //해당 유저가 저장한 좋아요 한 목록만 출력
        List<LikeEntity> boardlike = likeRepository.findByUserId(userId);
        return boardlike.stream().map(LikeResponseDto::from).collect(Collectors.toList());
    }

    @Transactional // 해당 유저의 게시물 아이디와 유저
    public List<LikeResponseDto> findByStatus(Integer boardId, UserEntity user) {
        List<LikeEntity> likeEntityList = likeRepository.findByStatus(boardId, user);
        return likeEntityList.stream().map(likeEntity -> {
            LikeResponseDto responseDto = LikeResponseDto.builder()
                    .build().convertEntityToDto(likeEntity);
            return responseDto;
        }).collect(Collectors.toList());
    }

    @Transactional
    public LikeResponseDto findByBoardLikeId(Integer id) {
        Optional<LikeEntity> boardLikeEntityWrapper = likeRepository.findById(id);
        LikeEntity likeEntity = boardLikeEntityWrapper.get();
        return LikeResponseDto.builder().build().convertEntityToDto(likeEntity);
    }

    @Transactional
    public void deleteById(Integer id, UserEntity user) {
        BoardDto boardDto = boardService.findById(this.findByBoardLikeId(id).getBoard().getId());
        //좋아요 테이블 고유번호로 찾은 보드 게시물에 좋아요 취소,삭제시 likecount -1 해줘야함
        int likecount = boardDto.getLikecount();
        if (likecount >= 1) {
            likecount = likecount - 1;
            boardDto.setLikecount(likecount);
            boardService.save(boardDto);
        }
        likeRepository.deleteByIdAndUser(id, user);
    }
}

