package com.study.kioskbackend.domain.admin.controller;

import com.study.kioskbackend.domain.admin.dto.FileDto;
import com.study.kioskbackend.domain.admin.dto.MenuResponseDto;
import com.study.kioskbackend.domain.admin.dto.CategoryResponseDto;
import com.study.kioskbackend.domain.admin.dto.MenuUpdateRequestDto;
import com.study.kioskbackend.domain.admin.service.AdminMenuService;
import com.study.kioskbackend.domain.menu.entity.Menu;
import com.study.kioskbackend.global.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminMenuController {
    private final AdminMenuService adminMenuService;

    @GetMapping("/categories")
    public ResponseDto<List<CategoryResponseDto>> categoryList(){
        List<CategoryResponseDto> categoryList = adminMenuService.findAllCategory();

        return ResponseDto.success(categoryList);
    }

    @GetMapping("/menu")
    public ResponseDto<Page<MenuResponseDto>> category_menu(@RequestParam Long categoryId,
                                                            @RequestParam(value = "page", defaultValue = "0") int page){
        Page<MenuResponseDto> menuList = adminMenuService.findByCategoryMenus(categoryId, page);
        return ResponseDto.success(menuList);
    }

    @GetMapping("/menu/{menuIdx}")
    public ResponseDto<MenuResponseDto> menuInfo(@PathVariable Long menuIdx){
        MenuResponseDto menu;
        try {
            menu = adminMenuService.findByMenuIdx(menuIdx);
        }catch (IllegalArgumentException e){
            return ResponseDto.fail("500", e.getMessage());
        }
        return ResponseDto.success(menu);
    }

    @PatchMapping("/image/{imageIdx}")
    public ResponseDto<Void> imageUpdate(@PathVariable Long imageIdx, @RequestBody MultipartFile[] uploadfile) throws IOException {
        // 이미지 파일 프론트엔드 폴더에 저장
        String fileSrc = adminMenuService.findByImage(imageIdx);
        for(MultipartFile file:uploadfile){
            if(!file.isEmpty()){
                FileDto dto = new FileDto(UUID.randomUUID().toString(),
                        file.getOriginalFilename(),
                        file.getContentType());

                File newFileName = new File(dto.getUuid()+'_'+dto.getFileName());
                fileSrc = newFileName.getName();
                file.transferTo(newFileName);
            }
        }
        // DB에 변경한 이미지 이름 저장
        try{
            adminMenuService.updateImage(imageIdx, fileSrc);
        }catch (IllegalArgumentException e){
            return ResponseDto.fail("500", e.getMessage());
        }catch (Exception e) {
            return ResponseDto.fail("500", "이미지 수정을 실패하였습니다.");
        }

        return ResponseDto.successWithNoData();
    }

    @PatchMapping("/menu/{menuIdx}")
    public ResponseDto<Void> menuUpdate(@PathVariable Long menuIdx, @RequestBody MenuUpdateRequestDto dto){
        try{
            Menu menu = adminMenuService.updateMenu(menuIdx, dto);
            if(Objects.equals(menu.getMenuIdx(), menuIdx)){
                return ResponseDto.successWithNoData();
            }else{
                return ResponseDto.fail("500", "상품 정보 수정을 실패하였습니다.");
            }
        }catch (IllegalArgumentException e){
            return ResponseDto.fail("500", e.getMessage());
        }
    }

    @DeleteMapping("/menu/{menuIdx}")
    public ResponseDto<Void> menuDelete(@PathVariable Long menuIdx){
        try{
            adminMenuService.deleteMenu(menuIdx);
        }catch (IllegalArgumentException e){
            return ResponseDto.fail("500", "상품 정보 삭제를 실패하였습니다.");
        }
        return ResponseDto.successWithNoData();
    }
}
