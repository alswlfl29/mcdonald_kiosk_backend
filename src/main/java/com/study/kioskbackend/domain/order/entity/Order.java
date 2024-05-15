package com.study.kioskbackend.domain.order.entity;

import com.study.kioskbackend.domain.admin.dto.OrderEditRequestDto;
import com.study.kioskbackend.domain.order.dto.OrderResponseDto;
import com.study.kioskbackend.domain.order.enumeration.OrderStatus;
import com.study.kioskbackend.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "order_menu")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderIdx;

    private String orderCode;

    private int orderPrice;

    private int orderCount;

    private int orderNumber;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    public void editOrder(Long idx, OrderEditRequestDto req) {
        this.orderIdx=idx;
        this.orderCount=req.getOrderCount();
        this.orderPrice=req.getOrderPrice();
    }

    public void deleteOrder(Long idx) {
        this.orderIdx=idx;
    }

    public static OrderResponseDto toDto(Order order,int userPoint) {
        return OrderResponseDto.builder()
                .userPoint(userPoint)
                .orderCode(order.orderCode)
                .orderCount(order.orderCount)
                .orderNumber(order.orderNumber)
                .orderPrice(order.getOrderPrice())
                .build();
    }


}
