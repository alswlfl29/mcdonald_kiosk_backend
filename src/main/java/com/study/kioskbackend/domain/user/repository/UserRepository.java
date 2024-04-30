package com.study.kioskbackend.domain.user.repository;

import com.study.kioskbackend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);
//    Optional<User> findByUserIdAndPassword(String userId, String userPw);
}

