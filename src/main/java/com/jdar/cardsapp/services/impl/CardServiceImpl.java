package com.jdar.cardsapp.services.impl;

import com.jdar.cardsapp.crosscutting.constants.TransactionTypes;
import com.jdar.cardsapp.crosscutting.utils.UtilClass;
import com.jdar.cardsapp.exceptions.servererror.TechnicalException;
import com.jdar.cardsapp.exceptions.successful.NoDataException;
import com.jdar.cardsapp.models.dto.CardDto;
import com.jdar.cardsapp.models.entity.CardEntity;
import com.jdar.cardsapp.models.entity.CardTypeEntity;
import com.jdar.cardsapp.models.entity.UserEntity;
import com.jdar.cardsapp.models.mapper.Mapper;
import com.jdar.cardsapp.models.repository.ICardRepository;
import com.jdar.cardsapp.models.repository.ICardTypeRepository;
import com.jdar.cardsapp.models.repository.IUserRepository;
import com.jdar.cardsapp.services.ICardService;
import com.jdar.cardsapp.services.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements ICardService {

    private final IUserRepository userRepository;
    private final ICardRepository cardRepository;
    private final ICardTypeRepository cardTypeRepository;
    private final ITransactionService transactionService;

    @Override
    @Transactional
    public CardDto createCardService(String productId, UUID userId) {
        try {
            UserEntity user = userRepository.findById(userId)
                    .orElseThrow(() -> new NoDataException(String.format("User with ID [%s] not found", userId)));

            CardTypeEntity cardType = cardTypeRepository.findByCardTypeCode(productId)
                    .orElseThrow(() -> new NoDataException(String.format("Product type [%s] not found", productId)));

            CardEntity card = CardEntity.builder()
                    .cardNumber(productId + UtilClass.generateRandomNumber())
                    .cardDueDate(UtilClass.generateDueDate())
                    .cardIsActive(0)
                    .cardIsBlock(0)
                    .cardBalance(BigDecimal.valueOf(0.00D))
                    .cardType(cardType)
                    .user(user)
                    .build();

            CardEntity cardSaved = cardRepository.save(card);
            transactionService.saveCardTransaction(cardSaved, TransactionTypes.CREATION_CARD_TX, new BigDecimal(0));

            return Mapper.mapCardEntityToDto(cardSaved);
        } catch (Exception exception) {
            throw new TechnicalException(String.format("Error at CardServiceImpl::createCardService -> [%s]", exception.getMessage()));
        }
    }

    @Override
    @Transactional
    public CardDto enrollCardService(CardDto cardDto) {
        try {
            CardEntity card = cardRepository.findByCardNumber(cardDto.getCardNumber())
                    .orElseThrow(() -> new NoDataException(String.format("Card with ID -> [%s] not found", cardDto.getCardNumber())));

            if (Objects.equals(card.getCardIsActive(), 1)) {
                throw new NoDataException(String.format("Card [%s] is already enrolled", cardDto.getCardNumber()));
            }

            card.setCardIsActive(1);
            CardEntity cardSaved = cardRepository.save(card);
            transactionService.saveCardTransaction(cardSaved, TransactionTypes.ACTIVATION_CARD_TX, new BigDecimal(0));

            return Mapper.mapCardEntityToDto(cardSaved);
        } catch (Exception exception) {
            throw new TechnicalException(String.format("Error at CardServiceImpl::enrollCardService -> [%s]", exception.getMessage()));
        }
    }

    @Override
    public CardDto lockCardService(String cardId) {
        try {
            CardEntity card = cardRepository.findByCardNumber(cardId)
                    .orElseThrow(() -> new NoDataException(String.format("Card with ID -> [%s] not found", cardId)));

            if (Objects.equals(card.getCardIsBlock(), 1)) {
                throw new NoDataException(String.format("Card [%s] is already locked", cardId));
            }

            card.setCardIsBlock(1);
            CardEntity cardSaved = cardRepository.save(card);
            transactionService.saveCardTransaction(cardSaved, TransactionTypes.LOCK_CARD_TX, new BigDecimal(0));

            return Mapper.mapCardEntityToDto(cardSaved);
        } catch (Exception exception) {
            throw new TechnicalException(String.format("Error at CardServiceImpl::blockCardService -> [%s]", exception.getMessage()));
        }
    }

    @Override
    public CardDto upBalanceService(CardDto cardDto) {
        try {
            CardEntity card = cardRepository.findByCardNumber(cardDto.getCardNumber())
                    .orElseThrow(() -> new NoDataException(String.format("Card with ID -> [%s] not found", cardDto.getCardNumber())));

            if (Objects.equals(card.getCardIsBlock(), 1) || Objects.equals(card.getCardIsActive(), 0)) {
                throw new NoDataException(String.format("Card [%s] is locked or is not longer active", cardDto.getCardNumber()));
            }

            BigDecimal amount = card.getCardBalance();
            amount = amount.add(new BigDecimal(String.valueOf(cardDto.getCardBalance())));

            card.setCardBalance(amount);
            CardEntity cardSaved = cardRepository.save(card);
            transactionService.saveCardTransaction(cardSaved, TransactionTypes.UP_BALANCE_TX, new BigDecimal(0));

            return Mapper.mapCardEntityToDto(cardSaved);
        } catch (Exception exception) {
            throw new TechnicalException(String.format("Error at CardServiceImpl::upBalanceService -> [%s]", exception.getMessage()));
        }
    }

    @Override
    public String getCardBalanceService(String cardId) {
        try {
            CardEntity card = cardRepository.findByCardNumber(cardId)
                    .orElseThrow(() -> new NoDataException(String.format("Card with ID -> [%s] not found", cardId)));

            return String.valueOf(card.getCardBalance());
        } catch (Exception exception) {
            throw new TechnicalException(String.format("Error at CardServiceImpl::getCardBalanceService -> [%s]", exception.getMessage()));
        }
    }

}
