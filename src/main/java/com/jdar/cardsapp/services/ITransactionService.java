package com.jdar.cardsapp.services;

import com.jdar.cardsapp.crosscutting.constants.TransactionTypes;
import com.jdar.cardsapp.models.dto.AnnulationDto;
import com.jdar.cardsapp.models.dto.PurchaseDto;
import com.jdar.cardsapp.models.dto.TransactionDto;
import com.jdar.cardsapp.models.entity.CardEntity;

import java.math.BigDecimal;
import java.util.UUID;

public interface ITransactionService {

    void saveCardTransaction(CardEntity card, TransactionTypes transactionType, BigDecimal amount);
    TransactionDto saveBuyTransaction(PurchaseDto purchaseDto);
    TransactionDto getTransactionById(UUID transactionId);
    String cancelTransaction(AnnulationDto annulationDto);

}