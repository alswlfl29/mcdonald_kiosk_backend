package com.study.kioskbackend.domain.user.controller;

import com.study.kioskbackend.domain.user.dto.JoinRequestDto;
import com.study.kioskbackend.domain.user.dto.LoginRequestDto;
import com.study.kioskbackend.domain.user.dto.UserDataDto;
import com.study.kioskbackend.domain.user.entity.User;
import com.study.kioskbackend.domain.user.service.UserService;
import com.study.kioskbackend.global.common.dto.ResponseDto;
import com.study.kioskbackend.global.config.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtTokenProvider;

    @PostMapping("/join")
    public ResponseDto<Void> join(@Valid @RequestBody JoinRequestDto dto) {
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(dto.getUserPw());
        dto.setUserPw(encodedPassword);

        return userService.join(dto);
    }

    @PostMapping("/login")
    public ResponseDto<Object> login(@RequestBody LoginRequestDto request) {
        // 아이디와 비밀번호로 사용자를 조회
        User user;
        try {
            user = userService.findByUserIdAndPassword(request.getUserId(), request.getUserPw());
        } catch (UsernameNotFoundException | BadCredentialsException ex) {
            return ResponseDto.fail("ERROR_CODE", ex.getMessage());
        }

        // 토큰 생성
        String token = jwtTokenProvider.createToken(user.getUserId(), user.getUserRole());

        UserDataDto userDataDto = new UserDataDto(token, user.getUserName());
        // 토큰을 포함한 응답 반환
        return ResponseDto.success(userDataDto);
    }

}
