package com.example.receiptprocessor.repositories;

import com.example.receiptprocessor.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, UUID> {
}
