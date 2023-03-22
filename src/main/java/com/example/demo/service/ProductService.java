package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.SellerDTO;
import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDTO> findAll() {

        List<ProductEntity> productEntityList=productRepository.findAll();
        List<ProductDTO> productDTOList=new ArrayList<>();

        for(ProductEntity productEntity:productEntityList){
            productDTOList.add(ProductDTO.toProductDTO(productEntity));
        }
        return productDTOList;
    }

    public void deleteBySeq(int productSeq) {

        productRepository.deleteById(productSeq);

    }

    public ProductDTO findBySeq(int productSeq) {

        Optional<ProductEntity> optionalProduct = productRepository.findById(productSeq);

        if (optionalProduct.isPresent()){

            return ProductDTO.toProductDTO(optionalProduct.get());

        }else{

            //null 처리 하기
            return null;

        }


    }


    public int findSellerSeqByProductSeq(int productSeq) {

        int sellerSeq=productRepository.findById(productSeq).get().getSellerEntity().getSellerSeq();

        return sellerSeq;

    }

    public void update(ProductDTO productDTO) {

        productRepository.save(ProductEntity.toUpdateProductEntity(productDTO));

    }

    public String findByProductSeq(int productSeq) {

        String productName = productRepository.findById(productSeq).get().getProductName();

        return productName;

    }
}//class
