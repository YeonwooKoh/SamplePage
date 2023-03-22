package com.example.demo.entity;

import com.example.demo.dto.OrderDTO;
import com.example.demo.repository.OrderRepository;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderSeq;

    @Column
    private int customerSeq;

    @Column
    private int productSeq;

    @Column
    private String productName;

    @Column
    private int sellerSeq;

    @Column
    private int orderAmount;

    public static OrderEntity toOrderEntity(OrderDTO orderDTO) {

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setOrderSeq(orderDTO.getOrderSeq());
        orderEntity.setCustomerSeq(orderDTO.getCustomerSeq());
        orderEntity.setProductSeq(orderDTO.getProductSeq());
        orderEntity.setProductName(orderDTO.getProductName());
        orderEntity.setSellerSeq(orderDTO.getSellerSeq());
        orderEntity.setOrderAmount(orderDTO.getOrderAmount());

        return orderEntity;

    }

    //가닥 잡히면 FK 생각하기

}//class
