package com.study.kioskbackend.domain.user.service;

import com.study.kioskbackend.domain.user.dto.JoinRequestDto;
import com.study.kioskbackend.domain.user.entity.User;
import com.study.kioskbackend.domain.user.enumeration.UserRole;
import com.study.kioskbackend.domain.user.repository.UserRepository;
import com.study.kioskbackend.global.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public ResponseDto<Void> join(JoinRequestDto request){
        User user = User.builder()
                .userId(request.getUserId())
                .userPw(request.getUserPw())
                .userName(request.getUserName())
                .userRole(UserRole.ROLE_USER)
                .userPoint(0)
                .userCreateDate(LocalDateTime.now())
                .userUpdateDate(null)
                .isDeleted(false)
                .build();

        try {
            userRepository.save(user);
            return ResponseDto.successWithNoData();
        } catch (Exception e) {
            return ResponseDto.fail("ERROR_CODE", "회원가입 실패: " + e.getMessage());
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));
        return new org.springframework.security.core.userdetails.User(
                user.getUserId(),
                user.getUserPw(),
                true,
                true,
                true,
                true,
                AuthorityUtils.createAuthorityList(user.getUserRole().toString())
        );
    }

    public User findByUserIdAndPassword(String userId, String password) {
        // 아이디로 사용자를 조회
        Optional<User> optionalUser = userRepository.findByUserId(userId);

        // 조회된 사용자가 없다면 예외를 던집니다.
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + userId));

        // 비밀번호 일치 여부 확인
        if (!passwordEncoder.matches(password, user.getUserPw())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }


}
