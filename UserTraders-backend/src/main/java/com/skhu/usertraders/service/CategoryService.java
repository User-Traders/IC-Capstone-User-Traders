package com.skhu.usertraders.service;

import com.skhu.usertraders.domain.entity.CategoryEntity;
import com.skhu.usertraders.domain.entity.DepartmentEntity;
import com.skhu.usertraders.domain.repository.CategoryRepository;
import com.skhu.usertraders.dto.CategoryDto;
import com.skhu.usertraders.dto.DepartmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public List<CategoryDto> findAll() { //전체 카테고리
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();

        List<CategoryDto> results = categoryEntityList.stream().map(categoryEntity -> {
            CategoryDto categoryDto = convertEntityToDto(categoryEntity);
            return categoryDto;
        }).collect(Collectors.toList());

        return results;
    }


    private CategoryDto convertEntityToDto(CategoryEntity categoryEntity) { //엔티티 객체 변수를 디티오 객체 변수로 변환
        return CategoryDto.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .build();
    }
}

