package com.study.kioskbackend.domain.order.repository;

import com.study.kioskbackend.domain.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("SELECT o FROM Order o WHERE o.isDeleted = false")
    Page<Order> findAllOrderList(Pageable pageable);

    @Query(value = "SELECT o FROM Order o WHERE DATE(o.createDate) = :createDate ORDER BY o.orderNumber DESC limit 1")
    Optional<Order> findLatestOrderNumberByOrderTime(@Param("createDate") LocalDate createDate);
}
