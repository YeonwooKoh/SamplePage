package com.example.demo.dto;

import com.example.demo.entity.SellerEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class SellerDTO {

    private int sellerSeq;
    private int userFlag;

    private String sellerId;
    private String sellerPw;
    private String sellerName;
    private String sellerAddress;

    public static SellerDTO toSellerDTO(SellerEntity sellerEntity) {

        SellerDTO sellerDTO = new SellerDTO();

        sellerDTO.setSellerSeq(sellerEntity.getSellerSeq());
        sellerDTO.setUserFlag(sellerEntity.getUserFlag());
        sellerDTO.setSellerId(sellerEntity.getSellerId());
        sellerDTO.setSellerPw(sellerEntity.getSellerPw());
        sellerDTO.setSellerName(sellerEntity.getSellerName());
        sellerDTO.setSellerAddress(sellerEntity.getSellerAddress());

        return sellerDTO;

    }
}//class
