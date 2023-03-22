package com.example.demo.dto;

import com.example.demo.entity.CustomerEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class CustomerDTO {

    private int customerSeq;
    private int userFlag;

    private String customerId;
    private String customerPw;
    private String customerName;
    private String customerAddress;

    public static CustomerDTO toCustomerDTO(CustomerEntity customerEntity){

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setCustomerSeq(customerEntity.getCustomerSeq());
        customerDTO.setUserFlag(customerEntity.getUserFlag());
        customerDTO.setCustomerId(customerEntity.getCustomerId());
        customerDTO.setCustomerPw(customerEntity.getCustomerPw());
        customerDTO.setCustomerName(customerEntity.getCustomerName());
        customerDTO.setCustomerAddress(customerEntity.getCustomerAddress());

        return customerDTO;

    }



}//class
