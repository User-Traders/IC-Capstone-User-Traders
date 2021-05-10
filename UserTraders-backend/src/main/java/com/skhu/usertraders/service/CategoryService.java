package com.skhu.usertraders.service;

import com.skhu.usertraders.domain.entity.CategoryEntity;
import com.skhu.usertraders.domain.repository.CategoryRepository;
import com.skhu.usertraders.dto.board.CategoryDto;
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
            CategoryDto categoryDto = CategoryDto.builder().
                    build().convertEntityToDto(categoryEntity);
            return categoryDto;
        }).collect(Collectors.toList());

        return results;
    }
}

