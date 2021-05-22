package com.skhu.usertraders.service;

import com.skhu.usertraders.domain.entity.GradeEntity;
import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.domain.repository.GradeRepository;
import com.skhu.usertraders.dto.board.BoardDto;
import com.skhu.usertraders.dto.grade.GradeRequestDto;
import com.skhu.usertraders.dto.grade.GradeResponseAllDto;
import com.skhu.usertraders.dto.grade.GradeResponseAvggradeDto;
import com.skhu.usertraders.dto.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeService {
    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    CustomUserDetailService userService;

    @Transactional
    public Integer save(GradeRequestDto requestDto, UserEntity user) {
        UserDto recvuser = userService.findByUserId(requestDto.getRecvUserId());

        GradeEntity gradeEntity = GradeEntity.builder()
                .sentId(user)
                .recvId(recvuser.convertDtoToEntity())
                .grade(requestDto.getGrade())
                .build();

        return gradeRepository.save(gradeEntity).getId();
    }

    public List<GradeResponseAllDto> findAll() {
        List<GradeEntity> gradeEntityList = gradeRepository.findAll();
        List<GradeResponseAllDto> results = gradeEntityList.stream().map(gradeEntity -> {
            GradeResponseAllDto gradeResponseAllDto = GradeResponseAllDto.builder()
                    .id(gradeEntity.getId())
                    .sentId(gradeEntity.getSentId())
                    .recvId(gradeEntity.getRecvId())
                    .build();
            return gradeResponseAllDto;
        }).collect(Collectors.toList());
        return results;
    }


    public double findGradeAvgByRecvId(String recvId) {
        System.out.println(userService.findByUserId(recvId));
        List<GradeEntity> gradeEntityList = gradeRepository.findByRecvId(userService.findByUserId(recvId).convertDtoToEntity());

        List<GradeResponseAvggradeDto> results = gradeEntityList.stream().map(gradeEntity -> {
            GradeResponseAvggradeDto responseAvggradeDto = GradeResponseAvggradeDto.builder()
                    .grade(gradeEntity.getGrade())
                    .build();
            return responseAvggradeDto;
        }).collect(Collectors.toList());

        double sum = 0;
        for (int i = 0; i < results.size(); i++) {
            sum = sum + results.get(i).getGrade();
        }

        return sum/results.size();
    }
}
