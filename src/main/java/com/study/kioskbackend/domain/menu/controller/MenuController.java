package com.study.kioskbackend.domain.menu.controller;

import com.study.kioskbackend.domain.menu.dto.CategoryMenuResponseDto;
import com.study.kioskbackend.domain.menu.service.MenuService;
import com.study.kioskbackend.global.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;

    @GetMapping("/recommend")
    public ResponseEntity<ResponseDto<Page<CategoryMenuResponseDto>>> getRecommendMenu(@RequestParam(value = "page", defaultValue = "0") int page) {
        Page<CategoryMenuResponseDto> menuList = menuService.getRecommendMenu(page);
        return ResponseEntity.accepted().body(ResponseDto.success(menuList));
    }

    @GetMapping()
    public ResponseDto<Page<CategoryMenuResponseDto>> getMenu(
            @RequestParam Long categoryId,
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        Page<CategoryMenuResponseDto> menuList = menuService.getMenus(categoryId, page);
        return ResponseDto.success(menuList);
    }
}
