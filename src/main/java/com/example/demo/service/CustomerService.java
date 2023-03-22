package com.example.demo.service;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.CustomerEntity;
import com.example.demo.entity.OrderEntity;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public void signin(CustomerDTO customerDTO){

        CustomerEntity customerEntity = CustomerEntity.toCustomerEntity(customerDTO);

        customerRepository.save(customerEntity);

    }

    public CustomerDTO login(CustomerDTO userDTO) {

        Optional<CustomerEntity> byCustomerId = customerRepository.findByCustomerId(userDTO.getCustomerId());

        if (byCustomerId.isPresent()){

            CustomerEntity customerEntity = byCustomerId.get();

            if (customerEntity.getCustomerPw().equals(userDTO.getCustomerPw())){

                CustomerDTO dto=CustomerDTO.toCustomerDTO(customerEntity);
                return dto;

            }else{
                //여기 나중에 처리하기
                //비밀번호 불일치
                return null;
            }
        } else{
            //조회 결과 없음
            return null;
        }

    }//login


    public CustomerDTO findByCustomerId(String myId) {

        Optional<CustomerEntity> optionalCustomerEntity=customerRepository.findByCustomerId(myId);

        CustomerDTO dto=CustomerDTO.toCustomerDTO(optionalCustomerEntity.get());

        return dto;

    }

    public List<OrderDTO> findByCustomerSeq(int customerSeq) {

        List<OrderEntity> myOrderEntityList = orderRepository.findByCustomerSeq(customerSeq);
        List<OrderDTO> myOrderDTOList = new ArrayList<>();

        for(OrderEntity orderEntity:myOrderEntityList){

            myOrderDTOList.add(OrderDTO.toOrderDTO(orderEntity));
        }

        return myOrderDTOList;

    }
}//class
