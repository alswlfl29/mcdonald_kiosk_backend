package com.study.kioskbackend.domain.admin.controller;

import com.study.kioskbackend.domain.admin.dto.UserEditRequestDto;
import com.study.kioskbackend.domain.admin.dto.UserResponseDto;
import com.study.kioskbackend.domain.admin.service.AdminUserService;
import com.study.kioskbackend.domain.user.entity.User;
import com.study.kioskbackend.global.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminUserController {
    private final AdminUserService adminUserService;

    @GetMapping("/user")
    public ResponseDto<Page<UserResponseDto>> getUserList(@RequestParam(value = "page",defaultValue = "0") int page) {
        return ResponseDto.success(adminUserService.getUserList(page));
    }

    @PatchMapping("/user/{id}")
    public ResponseDto<User> editUser(@PathVariable("id") Long id, @RequestBody UserEditRequestDto userEditRequestDto) {
        return adminUserService.editUser(id, userEditRequestDto);
    }

    @GetMapping("/user/{id}")
    public ResponseDto<UserResponseDto> getOrderDetail(@PathVariable("id") Long userIdx) {
        return adminUserService.orderDetail(userIdx);
    }

    @DeleteMapping("/user/{id}")
    public ResponseDto<Void> deleteOrder(@PathVariable("id") Long userIdx) {
        return adminUserService.deleteOrder(userIdx);
    }

}

