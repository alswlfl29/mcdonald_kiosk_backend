package com.study.kioskbackend.domain.menu.service;

import com.study.kioskbackend.domain.menu.dto.CategoryMenuResponseDto;
import com.study.kioskbackend.domain.menu.entity.Menu;
import com.study.kioskbackend.domain.menu.repository.ImageRepository;
import com.study.kioskbackend.domain.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final ImageRepository imageRepository;

    @Transactional(readOnly = true)
    public Page<CategoryMenuResponseDto> getRecommendMenu(int page){
        Pageable pageable = PageRequest.of(page, 9, Sort.by(Sort.Order.asc("createDate")));

        Page<Menu> menusEntity = menuRepository.findByMenuRecommend(pageable);

        return menusEntity.map((menu) -> new CategoryMenuResponseDto(menu,
                                imageRepository.findById(menu.getImgIdx())
                                        .orElseThrow(() -> new IllegalArgumentException("이미지가 존재하지 않습니다")).getImgUrl()));
    }

    @Transactional(readOnly = true)
    public Page<CategoryMenuResponseDto> getMenus(Long categoryIdx, int page){
        Pageable pageable = PageRequest.of(page, 9, Sort.by(Sort.Order.desc("createDate")));

        Page<Menu> menusEntity = menuRepository.findByCategoryIdxAndIsDeleted(categoryIdx, pageable);
        return menusEntity.map((menu) -> new CategoryMenuResponseDto(menu,
                                imageRepository.findById(menu.getImgIdx())
                                        .orElseThrow(() -> new IllegalArgumentException("이미지가 존재하지 않습니다"))
                                        .getImgUrl()));
    }
}
