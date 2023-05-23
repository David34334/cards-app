package com.jdar.cardsapp.models.repository;

import com.jdar.cardsapp.models.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ITransactionRepository extends JpaRepository<TransactionEntity, UUID> {

}