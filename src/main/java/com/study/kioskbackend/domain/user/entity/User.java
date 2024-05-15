package com.study.kioskbackend.domain.user.entity;

import com.study.kioskbackend.domain.admin.dto.UserEditRequestDto;
import com.study.kioskbackend.domain.user.enumeration.UserRole;
import com.study.kioskbackend.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx")
    private Long userIdx;
    @Column(name = "user_id", unique = true, nullable = false)
    private String userId;
    @Column(name = "user_pw", nullable = false)
    private String userPw;
    @Column(name = "user_name", nullable = false)
    private String userName;
    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @Column(name = "user_point")
    private int userPoint;
    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Builder
    public User(Long userIdx, String userId, String userPw, String userName, UserRole userRole, int userPoint,
                boolean isDeleted) {
        this.userIdx = userIdx;
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userRole = userRole;
        this.userPoint = userPoint;
        this.isDeleted = isDeleted;
    }

    public User() {
    }

    public void updatePoint(int point) {
        this.userPoint = point;
    }


    public void editUser(Long userIdx, UserEditRequestDto req) {
        this.userIdx = userIdx;
        this.userId = req.getUserId();
        this.userPw = req.getUserPw();
        this.userName = req.getUserName();
        this.userRole = req.getUserRole();
        this.userPoint = req.getUserPoint();
    }

    public void deleteOrder(Long userIdx) {
        this.userIdx = userIdx;
        this.isDeleted = true;
    }
}