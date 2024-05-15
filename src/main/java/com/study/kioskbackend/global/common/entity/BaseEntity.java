package com.study.kioskbackend.global.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
public abstract class BaseEntity {
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @LastModifiedDate
    private LocalDateTime updateDate;
}
