package com.study.kioskbackend.domain.menu.entity;

import com.study.kioskbackend.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_idx")
    private Long menuIdx;

    @Column(name = "category_idx", nullable = false)
    private Long categoryIdx;

    @Column(name = "img_idx", nullable = false)
    private Long imgIdx;

    @Column(name="menu_name", nullable = false)
    private String menuName;

    @Column(name = "menu_price", nullable = false)
    private int menuPrice;

    @Column(name = "menu_calory", nullable = false)
    private int menuCalory;

    @Column(name = "menu_code", nullable = false, unique = true)
    private String menuCode;

    @Column(name="menu_recommend", nullable = false)
    private boolean menuRecommend;

    @Column(name = "is_deleted")
    private boolean isDeleted;


    public void update(String menuName, Long categoryIdx, int menuPrice, int menuCalory, boolean menuRecommend){
        this.menuName = menuName;
        this.categoryIdx = categoryIdx;
        this.menuPrice = menuPrice;
        this.menuCalory = menuCalory;
        this.menuRecommend = menuRecommend;
    }
}
