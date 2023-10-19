package com.DesafioPicPay.repository;

import com.DesafioPicPay.entity.Person;
import com.DesafioPicPay.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {
    Optional<Transfer> findById(Long id);
}
