package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.SellerDTO;
import com.example.demo.entity.ProductEntity;
import com.example.demo.entity.SellerEntity;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;

    public void signin(SellerDTO sellerDTO) {

        SellerEntity sellerEntity=SellerEntity.toSellerEntity(sellerDTO);
        sellerRepository.save(sellerEntity);
    }

    public SellerDTO login(SellerDTO userDTO) {

        Optional<SellerEntity> bySellerId = sellerRepository.findBySellerId(userDTO.getSellerId());

        if (bySellerId.isPresent()){

            SellerEntity sellerEntity = bySellerId.get();

            if (sellerEntity.getSellerPw().equals(userDTO.getSellerPw())){

                SellerDTO dto =SellerDTO.toSellerDTO(sellerEntity);
                return dto;

            }else {
                //여기 나중에 처리하기
                //비밀번호 불일치
                return null;
            }
        }else {
            //조회 결과 없음
            return null;
        }
    }

    public int findSellerSeqBySellerId(String sellerId) {

        int sellerSeq = sellerRepository.findSellerSeqBySellerId(sellerId);

        return sellerSeq;
    }

    public void addProductPro(ProductDTO productDTO) {

        ProductEntity productEntity=ProductEntity.toProductEntity(productDTO);
        productRepository.save(productEntity);

    }

    public List<ProductDTO> findMyProduct(int sellerSeq) {


        List<ProductEntity> myProductEntityList =productRepository.findBySellerEntity_SellerSeq(sellerSeq);
        List<ProductDTO> myProductDTOList = new ArrayList<>();

        for (ProductEntity productEntity:myProductEntityList){

            myProductDTOList.add(ProductDTO.toProductDTO(productEntity));

        }

        return myProductDTOList;

    }

}//class
