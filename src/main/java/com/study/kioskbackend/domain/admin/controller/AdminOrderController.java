package com.study.kioskbackend.domain.admin.controller;

import com.study.kioskbackend.domain.admin.dto.OrderEditRequestDto;
import com.study.kioskbackend.domain.admin.dto.OrderListResponseDto;
import com.study.kioskbackend.domain.admin.service.AdminOrderService;
import com.study.kioskbackend.domain.order.entity.Order;
import com.study.kioskbackend.global.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminOrderController {

    private final AdminOrderService adminOrderService;

    @GetMapping("/order")
    public ResponseDto<Page<OrderListResponseDto>> getOrderList(@RequestParam(value = "page",defaultValue = "0") int page) {
        return ResponseDto.success(adminOrderService.getOrderList(page));
    }

    @PatchMapping("/order/{id}")
    public ResponseDto<Order> editOrder(@PathVariable("id") Long id, @RequestBody OrderEditRequestDto orderEditRequestDto) {
        return adminOrderService.editOrder(id,orderEditRequestDto);
    }

    @GetMapping("/order/{id}")
    public ResponseDto<OrderListResponseDto> getOrderDetail(@PathVariable("id") Long id) {
        return adminOrderService.orderDetail(id);
    }

    @DeleteMapping("/order/{id}")
    public ResponseDto<Void> deleteOrder(@PathVariable("id") Long id) {
        return adminOrderService.deleteOrder(id);
    }


}
