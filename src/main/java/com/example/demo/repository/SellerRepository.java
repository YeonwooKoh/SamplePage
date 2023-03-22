package com.example.demo.repository;

import com.example.demo.entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<SellerEntity, Integer> {

    Optional<SellerEntity> findBySellerId(String sellerId);

    @Query("Select s.sellerSeq from SellerEntity s where s.sellerId=:sellerId")
    Integer findSellerSeqBySellerId(@Param("sellerId") String sellerId);

}//class
