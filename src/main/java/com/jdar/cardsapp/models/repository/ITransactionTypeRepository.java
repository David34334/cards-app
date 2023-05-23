package com.jdar.cardsapp.models.repository;

import com.jdar.cardsapp.models.entity.TransactionTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ITransactionTypeRepository extends JpaRepository<TransactionTypeEntity, UUID> {

    Optional<TransactionTypeEntity> findByTransactionTypeNameKey(String transactionType);

}