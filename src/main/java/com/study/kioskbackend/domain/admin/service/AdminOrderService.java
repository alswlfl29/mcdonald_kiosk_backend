package com.study.kioskbackend.domain.admin.service;


import com.study.kioskbackend.domain.admin.dto.OrderEditRequestDto;
import com.study.kioskbackend.domain.admin.dto.OrderListResponseDto;
import com.study.kioskbackend.domain.order.entity.Order;
import com.study.kioskbackend.domain.order.repository.OrderRepository;
import com.study.kioskbackend.global.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminOrderService {

    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public Page<OrderListResponseDto> getOrderList(int page) {
        Pageable paging = PageRequest.of(page, 5, Sort.by( Sort.Order.desc("orderTime")));
        return orderRepository.findAllOrderList(paging).map(OrderListResponseDto::toDto);
    }

    @Transactional
    public ResponseDto<Order> editOrder(Long id, OrderEditRequestDto orderEditRequestDto) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("주문 목록을 찾을 수 없습니다."));

        order.editOrder(id,orderEditRequestDto);
        order = orderRepository.save(order);
        return ResponseDto.success(order);
    }

    @Transactional(readOnly = true)
    public ResponseDto<OrderListResponseDto> orderDetail(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()->  new IllegalArgumentException("주문 목록을 찾을 수 없습니다."));
        return ResponseDto.success(OrderListResponseDto.toDto(order));
    }

    @Transactional
    public ResponseDto<Void> deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()->  new IllegalArgumentException("주문 목록을 찾을 수 없습니다."));
        order.deleteOrder(id);
        orderRepository.save(order);
        return ResponseDto.successWithNoData();
    }


}
