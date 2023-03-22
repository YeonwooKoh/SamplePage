package com.example.demo.entity;

import com.example.demo.dto.CustomerDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="customer")

public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerSeq;

    @ColumnDefault("0")
    private int userFlag;

    @Column(unique = true)
    private String customerId;

    @Column
    private String customerPw;

    @Column
    private String customerName;

    @Column
    private String customerAddress;


    public static CustomerEntity toCustomerEntity(CustomerDTO customerDTO) {

        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setCustomerId(customerDTO.getCustomerId());
        customerEntity.setCustomerPw(customerDTO.getCustomerPw());
        customerEntity.setCustomerName(customerDTO.getCustomerName());
        customerEntity.setCustomerAddress(customerDTO.getCustomerAddress());

        return customerEntity;
    }


}//class
