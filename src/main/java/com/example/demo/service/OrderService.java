package com.example.demo.service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.OrderEntity;
import com.example.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void save(OrderDTO orderDTO) {

        System.out.println("체크1");
        OrderEntity orderEntity=OrderEntity.toOrderEntity(orderDTO);
        System.out.println("체크2");

        orderRepository.save(orderEntity);
        System.out.println("체크 final");

    }
}
