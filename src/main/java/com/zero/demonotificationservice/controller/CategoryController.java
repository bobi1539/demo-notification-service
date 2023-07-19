package com.zero.demonotificationservice.controller;

import com.zero.demonotificationservice.dto.request.CategoryRequestDto;
import com.zero.demonotificationservice.dto.response.CategoryResponseDto;
import com.zero.demonotificationservice.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto requestDto) {
        return categoryService.create(requestDto);
    }

    @GetMapping
    public List<CategoryResponseDto> getCategories() {
        return categoryService.getCategories();
    }
}
