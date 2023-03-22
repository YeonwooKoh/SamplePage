package com.example.demo.dto;

import com.example.demo.entity.OrderEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class OrderDTO {

    //PK
    private int orderSeq;
    private int customerSeq;
    private int productSeq;
    private String productName;
    private int sellerSeq;
    private int orderAmount;

    public static OrderDTO toOrderDTO(OrderEntity orderEntity) {

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderSeq(orderEntity.getOrderSeq());
        orderDTO.setCustomerSeq(orderEntity.getCustomerSeq());
        orderDTO.setProductSeq(orderEntity.getProductSeq());
        orderDTO.setProductName(orderEntity.getProductName());
        orderDTO.setSellerSeq(orderEntity.getSellerSeq());
        orderDTO.setOrderAmount(orderEntity.getOrderAmount());

        return orderDTO;
    }

    //컴토 : 보안 & FK



}
