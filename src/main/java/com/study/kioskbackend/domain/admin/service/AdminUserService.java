package com.study.kioskbackend.domain.admin.service;

import com.study.kioskbackend.domain.admin.dto.UserEditRequestDto;
import com.study.kioskbackend.domain.admin.dto.UserResponseDto;
import com.study.kioskbackend.domain.user.entity.User;
import com.study.kioskbackend.domain.user.repository.UserRepository;
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
public class AdminUserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<UserResponseDto> getUserList(int page) {
        Pageable paging = PageRequest.of(page, 5, Sort.by( Sort.Order.desc("userCreateDate")));
        return userRepository.findAllUserList(paging).map(UserResponseDto::fromEntity);
    }

    @Transactional
    public ResponseDto<User> editUser(Long userIdx, UserEditRequestDto userEditRequestDto) {
        User user = userRepository.findById(userIdx).orElseThrow(()-> new IllegalArgumentException("사용자를 찾을 수 없습니다"));

        user.editUser(userIdx,userEditRequestDto);
        user = userRepository.save(user);
        return ResponseDto.success(user);
    }

    @Transactional(readOnly = true)
    public ResponseDto<UserResponseDto> orderDetail(Long userIdx) {
        User user = userRepository.findById(userIdx).orElseThrow(()-> new IllegalArgumentException("사용자를 찾을 수 없습니다"));
        return ResponseDto.success(UserResponseDto.fromEntity(user));
    }

    @Transactional
    public ResponseDto<Void> deleteOrder(Long userIdx) {
        User user = userRepository.findById(userIdx).orElseThrow(()->  new IllegalArgumentException("사용자를 찾을 수 없습니다"));
        user.deleteOrder(userIdx);
        userRepository.save(user);
        return ResponseDto.successWithNoData();
    }


}
