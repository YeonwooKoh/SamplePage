package com.example.demo.entity;

import com.example.demo.dto.SellerDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="seller")
public class SellerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sellerSeq;

    @ColumnDefault("1")
    private int userFlag;

    @Column
    private String sellerId;

    @Column
    private String sellerPw;

    @Column
    private String sellerName;

    @Column
    private String sellerAddress;

    @OneToMany(mappedBy = "sellerEntity")
    private List<ProductEntity> products = new ArrayList<>();


    public static SellerEntity toSellerEntity(SellerDTO sellerDTO) {

        SellerEntity sellerEntity=new SellerEntity();

        sellerEntity.setSellerId(sellerDTO.getSellerId());
        sellerEntity.setSellerPw(sellerDTO.getSellerPw());
        sellerEntity.setSellerName(sellerDTO.getSellerName());
        sellerEntity.setSellerAddress(sellerDTO.getSellerAddress());

        return sellerEntity;

    }

}//class
