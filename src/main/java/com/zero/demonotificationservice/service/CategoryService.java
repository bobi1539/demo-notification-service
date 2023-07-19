package com.zero.demonotificationservice.service;

import com.zero.demonotificationservice.dto.request.CategoryRequestDto;
import com.zero.demonotificationservice.dto.response.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    CategoryResponseDto create(CategoryRequestDto requestDto);
    List<CategoryResponseDto> getCategories();
}
