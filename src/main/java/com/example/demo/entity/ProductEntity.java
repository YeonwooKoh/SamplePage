package com.example.demo.entity;

import com.example.demo.dto.ProductDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="product")

public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int productSeq;

    @ManyToOne
    @JoinColumn(name="sellerSeq")
    private SellerEntity sellerEntity;

    @Column
    private String productName;

    @Column
    private int productPrice;

    @Column
    private int productAmount;


    public static ProductEntity toProductEntity(ProductDTO productDTO) {

        ProductEntity productEntity = new ProductEntity();

        productEntity.setProductName(productDTO.getProductName());
        productEntity.setProductPrice(productDTO.getProductPrice());
        productEntity.setProductAmount(productDTO.getProductAmount());

        SellerEntity sellerEntity = new SellerEntity();

        sellerEntity.setSellerSeq(productDTO.getSellerSeq());
        productEntity.setSellerEntity(sellerEntity);

        return productEntity;

    }


    public static ProductEntity toUpdateProductEntity(ProductDTO productDTO) {

        ProductEntity productEntity = new ProductEntity();

        productEntity.setProductSeq(productDTO.getProductSeq());
        productEntity.setProductName(productDTO.getProductName());
        productEntity.setProductPrice(productDTO.getProductPrice());
        productEntity.setProductAmount(productDTO.getProductAmount());

        SellerEntity sellerEntity=new SellerEntity();

        sellerEntity.setSellerSeq(productDTO.getSellerSeq());
        productEntity.setSellerEntity(sellerEntity);

        return productEntity;

    }
}//class
