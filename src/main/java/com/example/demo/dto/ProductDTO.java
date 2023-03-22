package com.example.demo.dto;

import com.example.demo.entity.ProductEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class ProductDTO {

    private int productSeq;
    private int sellerSeq; //FK

    private String productName;
    private int productPrice;
    private int productAmount;


    public static ProductDTO toProductDTO(ProductEntity productEntity) {

        ProductDTO productDTO = new ProductDTO();

        productDTO.setProductSeq(productEntity.getProductSeq());
        productDTO.setProductName(productEntity.getProductName());
        productDTO.setProductPrice(productEntity.getProductPrice());
        productDTO.setProductAmount(productEntity.getProductAmount());
        productDTO.setSellerSeq(productEntity.getSellerEntity().getSellerSeq());

        return productDTO;
    }

}//class
