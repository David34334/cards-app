package com.jdar.cardsapp.models.mapper;

import com.jdar.cardsapp.models.dto.*;
import com.jdar.cardsapp.models.entity.CardEntity;
import com.jdar.cardsapp.models.entity.TransactionEntity;

public class Mapper {

    private Mapper() {super();}

    public static CardDto mapCardEntityToDto(CardEntity cardEntity) {
        return CardDto.builder()
                .id(cardEntity.getId())
                .cardNumber(cardEntity.getCardNumber())
                .cardDueDate(cardEntity.getCardDueDate())
                .cardIsActive(cardEntity.getCardIsActive())
                .cardIsBlock(cardEntity.getCardIsBlock())
                .cardBalance(cardEntity.getCardBalance())
                .cardType(CardTypeDto.builder()
                        .id(cardEntity.getCardType().getId())
                        .cardTypeCode(cardEntity.getCardType().getCardTypeCode())
                        .cardTypeDescription(cardEntity.getCardType().getCardTypeDescription())
                        .build())
                .user(UserDto.builder()
                        .id(cardEntity.getUser().getId())
                        .firstName(cardEntity.getUser().getFirstName())
                        .lastName(cardEntity.getUser().getLastName())
                        .email(cardEntity.getUser().getEmail())
                        .build())
                .build();
    }

    public static TransactionDto mapTransaction(TransactionEntity transactionEntity) {
        return TransactionDto.builder()
                .id(transactionEntity.getId())
                .transactionName(transactionEntity.getTransactionName())
                .transactionDate(transactionEntity.getTransactionDate())
                .transactionAmount(transactionEntity.getTransactionAmount())
                .transactionIsBlock(transactionEntity.getTransactionIsBlock())
                .transactionType(TransactionTypeDto.builder()
                        .id(transactionEntity.getTransactionType().getId())
                        .transactionTypeName(transactionEntity.getTransactionType().getTransactionTypeName())
                        .transactionTypeDescription(transactionEntity.getTransactionType().getTransactionTypeDescription())
                        .transactionTypeNameKey(transactionEntity.getTransactionType().getTransactionTypeNameKey())
                        .build())
                .transactionCard(CardDto.builder()
                        .id(transactionEntity.getTransactionCard().getId())
                        .cardNumber(transactionEntity.getTransactionCard().getCardNumber())
                        .cardDueDate(transactionEntity.getTransactionCard().getCardDueDate())
                        .cardIsActive(transactionEntity.getTransactionCard().getCardIsActive())
                        .cardIsBlock(transactionEntity.getTransactionCard().getCardIsBlock())
                        .cardBalance(transactionEntity.getTransactionCard().getCardBalance())
                        .cardType(CardTypeDto.builder()
                                .id(transactionEntity.getTransactionCard().getCardType().getId())
                                .cardTypeCode(transactionEntity.getTransactionCard().getCardType().getCardTypeCode())
                                .cardTypeDescription(transactionEntity.getTransactionCard().getCardType().getCardTypeDescription())
                                .build())
                        .user(UserDto.builder()
                                .id(transactionEntity.getTransactionCard().getUser().getId())
                                .firstName(transactionEntity.getTransactionCard().getUser().getFirstName())
                                .lastName(transactionEntity.getTransactionCard().getUser().getLastName())
                                .email(transactionEntity.getTransactionCard().getUser().getEmail())
                                .build())
                        .build())
                .build();
    }

}
