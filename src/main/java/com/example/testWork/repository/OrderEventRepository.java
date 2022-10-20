package com.example.testWork.repository;

import com.example.testWork.models.OrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderEventRepository extends JpaRepository<OrderEvent, Integer> {

    List<OrderEvent> findByOrderId(Integer orderId);
}
