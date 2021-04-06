package com.skhu.usertraders.service;

import com.skhu.usertraders.domain.entity.DepartmentEntity;
import com.skhu.usertraders.domain.repository.DepartmentRepository;
import com.skhu.usertraders.dto.DepartmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DepartmentService {


    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional
    public List<DepartmentDto> findDepartment() { // 전체 학과

        List<DepartmentEntity> departmentEntityList = new ArrayList<>();


        departmentEntityList.add(new DepartmentEntity(1, "신학과"));
        departmentEntityList.add(new DepartmentEntity(2, "영어학과"));
        departmentEntityList.add(new DepartmentEntity(3, "일어일본학과"));
        departmentEntityList.add(new DepartmentEntity(4, "중어중국학과"));
        departmentEntityList.add(new DepartmentEntity(5, "사회복지학과"));
        departmentEntityList.add(new DepartmentEntity(6, "사회학과"));
        departmentEntityList.add(new DepartmentEntity(7, "정치학과"));
        departmentEntityList.add(new DepartmentEntity(8, "경제학과"));
        departmentEntityList.add(new DepartmentEntity(9, "경영학과"));
        departmentEntityList.add(new DepartmentEntity(10, "신문방송학과"));
        departmentEntityList.add(new DepartmentEntity(11, "디지털컨텐츠학과"));
        departmentEntityList.add(new DepartmentEntity(12, "컴퓨터공학과"));
        departmentEntityList.add(new DepartmentEntity(13, "정보통신공학과"));
        departmentEntityList.add(new DepartmentEntity(14, "소프트공학과"));
        departmentEntityList.add(new DepartmentEntity(15, "글로컬IT학과"));


        List<DepartmentDto> results = departmentEntityList.stream().map(departmentEntity -> {
            DepartmentDto departmentDto = convertEntityToDto(departmentEntity);
            return departmentDto;
        }).collect(Collectors.toList());

        return results;
    }


    @Transactional
    public List<DepartmentDto> findAll() { // 전체 학과 2
        List<DepartmentEntity> departmentEntityList = departmentRepository.findAll();

        List<DepartmentDto> results = departmentEntityList.stream().map(departmentEntity -> {
            DepartmentDto departmentDto = convertEntityToDto(departmentEntity);
            return departmentDto;
        }).collect(Collectors.toList());


        return results;
    }


    private DepartmentDto convertEntityToDto(DepartmentEntity departmentEntity) { //엔티티 객체 변수를 디티오 객체 변수로 변환
        return DepartmentDto.builder()
                .id(departmentEntity.getId())
                .name(departmentEntity.getName())
                .build();
    }

}
