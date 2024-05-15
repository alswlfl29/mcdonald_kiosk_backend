package com.study.kioskbackend.domain.admin.dto;


import com.study.kioskbackend.domain.order.entity.Order;
import com.study.kioskbackend.domain.order.enumeration.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class OrderListResponseDto {

    private int idx;
    private String orderCode;
    private int orderPrice;
    private int orderCount;
    private int orderNumber;
    private OrderStatus orderStatus;
    private LocalDateTime createDate;

    public static OrderListResponseDto toDto(Order order){
        return OrderListResponseDto.builder()
                .idx(order.getOrderIdx().intValue())
                .orderCode(order.getOrderCode())
                .orderPrice(order.getOrderPrice())
                .orderCount(order.getOrderCount())
                .orderNumber(order.getOrderNumber())
                .orderStatus(order.getOrderStatus())
                .createDate(order.getCreateDate())
                .build();
    }

}
