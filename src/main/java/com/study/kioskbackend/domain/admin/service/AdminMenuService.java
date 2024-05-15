package com.study.kioskbackend.domain.admin.service;

import com.study.kioskbackend.domain.admin.dto.CategoryResponseDto;
import com.study.kioskbackend.domain.admin.dto.MenuResponseDto;
import com.study.kioskbackend.domain.admin.dto.MenuUpdateRequestDto;
import com.study.kioskbackend.domain.menu.entity.Category;
import com.study.kioskbackend.domain.menu.entity.Image;
import com.study.kioskbackend.domain.menu.entity.Menu;
import com.study.kioskbackend.domain.menu.repository.CategoryRepository;
import com.study.kioskbackend.domain.menu.repository.ImageRepository;
import com.study.kioskbackend.domain.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminMenuService {
    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;

    @Transactional(readOnly = true)
    public List<CategoryResponseDto> findAllCategory(){
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(CategoryResponseDto::new).toList();
    }

    @Transactional(readOnly = true)
    public Page<MenuResponseDto> findByCategoryMenus(Long categoryIdx, int page){
        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Order.desc("createDate")));

        Page<Menu> menusEntity = menuRepository.findByCategoryIdxAndIsDeleted(categoryIdx, pageable);

        return menusEntity.map((menu) -> new MenuResponseDto(menu,
                        imageRepository.findById(menu.getImgIdx())
                                .orElseThrow(() -> new IllegalArgumentException("이미지가 존재하지 않습니다.")).getImgUrl(),
                        categoryRepository.findById(menu.getCategoryIdx())
                                .orElseThrow(() -> new IllegalArgumentException("카테고리가 존재하지 않습니다.")).getCategoryTitle()
                ));
    }

    @Transactional(readOnly = true)
    public MenuResponseDto findByMenuIdx(Long menuIdx){
        Menu menu = menuRepository.findById(menuIdx)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
        return new MenuResponseDto(menu,
                imageRepository.findById(menu.getImgIdx())
                        .orElseThrow(() -> new IllegalArgumentException("이미지가 존재하지 않습니다.")).getImgUrl(),
                categoryRepository.findById(menu.getCategoryIdx())
                        .orElseThrow(() -> new IllegalArgumentException("카테고리가 존재하지 않습니다.")).getCategoryTitle());
    }

    @Transactional(readOnly = true)
    public String findByImage(final Long imageIdx){
        Image image = imageRepository.findById(imageIdx)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이미지입니다."));
        return image.getImgUrl();
    }

    @Transactional
    public void updateImage(final Long imageIdx, String newFileUrl){
        Image image = imageRepository.findById(imageIdx)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이미지입니다."));
        image.update(newFileUrl);
    }

    @Transactional
    public Menu updateMenu(final Long menuIdx, MenuUpdateRequestDto dto){
        Menu menu = menuRepository.findById(menuIdx)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
        menu.update(dto.getMenuName(), dto.getMenuCategory(), dto.getMenuPrice(), dto.getMenuCalory(), dto.isMenuRecommend());

        return menu;
    }

    @Transactional
    public void deleteMenu(final Long menuIdx){
        Menu menu = menuRepository.findById(menuIdx)
                .orElseThrow(() -> new IllegalArgumentException("상품 삭제를 실패하였습니다. [존재하지 않는 상품입니다.]"));
        menuRepository.deleteById(menu.getMenuIdx());
    }
}
