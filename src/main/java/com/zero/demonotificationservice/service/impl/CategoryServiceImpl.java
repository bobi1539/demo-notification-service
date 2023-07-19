package com.zero.demonotificationservice.service.impl;

import com.zero.demonotificationservice.dto.request.CategoryRequestDto;
import com.zero.demonotificationservice.dto.response.CategoryResponseDto;
import com.zero.demonotificationservice.entity.MCategory;
import com.zero.demonotificationservice.repository.CategoryRepository;
import com.zero.demonotificationservice.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDto create(CategoryRequestDto requestDto) {
        MCategory category = MCategory.builder()
                .name(requestDto.getName())
                .build();
        MCategory categorySaved = categoryRepository.save(category);
        return CategoryResponseDto.builder()
                .id(categorySaved.getId())
                .name(categorySaved.getName())
                .build();
    }

    @Override
    public List<CategoryResponseDto> getCategories() {
        List<MCategory> categories = categoryRepository.findAll();
        List<CategoryResponseDto> categoryResponseDtos = new ArrayList<>();
        categories.forEach(category -> {
            CategoryResponseDto categoryResponseDto = CategoryResponseDto.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build();
            categoryResponseDtos.add(categoryResponseDto);
        });
        return categoryResponseDtos;
    }
}
