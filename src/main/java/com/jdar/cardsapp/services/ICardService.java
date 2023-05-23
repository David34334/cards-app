package com.jdar.cardsapp.services;

import com.jdar.cardsapp.models.dto.CardDto;

import java.util.UUID;

public interface ICardService {

    CardDto createCardService(String productId, UUID userId);
    CardDto enrollCardService(CardDto cardDto);
    CardDto lockCardService(String cardId);
    CardDto upBalanceService(CardDto cardDto);
    String getCardBalanceService(String cardId);

}
