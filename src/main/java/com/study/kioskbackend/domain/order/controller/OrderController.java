package com.study.kioskbackend.domain.order.controller;

import com.study.kioskbackend.domain.order.dto.OrderRequestDto;
import com.study.kioskbackend.domain.order.dto.OrderResponseDto;
import com.study.kioskbackend.domain.order.service.OrderService;
import com.study.kioskbackend.domain.user.entity.PrincipalDetails;
import com.study.kioskbackend.global.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping()
    public ResponseDto<OrderResponseDto> order(@RequestBody OrderRequestDto orderRequestDto, @AuthenticationPrincipal PrincipalDetails user) {
        return orderService.order(orderRequestDto,user);
    }

}
