package com.study.kioskbackend.domain.admin.dto;

import com.study.kioskbackend.domain.menu.entity.Menu;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MenuResponseDto {
    private Long menuIdx;
    private Long imgIdx;
    private String imgSrc;
    private String menuName;
    private int menuPrice;
    private String menuOption;
    private int menuCalory;
    private String menuCode;
    private boolean menuRecommend;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime menuUpdateDate;

    public MenuResponseDto(Menu menu, String imageSrc, String categoryTitle){
        this.menuIdx = menu.getMenuIdx();
        this.imgIdx = menu.getImgIdx();
        this.imgSrc = imageSrc;
        this.menuName = menu.getMenuName();
        this.menuPrice = menu.getMenuPrice();
        this.menuOption = categoryTitle;
        this.menuCalory = menu.getMenuCalory();
        this.menuCode = menu.getMenuCode();
        this.menuRecommend = menu.isMenuRecommend();
    }
}
