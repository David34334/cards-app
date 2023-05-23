package com.jdar.cardsapp.services.impl;

import com.jdar.cardsapp.crosscutting.constants.TransactionTypes;
import com.jdar.cardsapp.exceptions.servererror.TechnicalException;
import com.jdar.cardsapp.exceptions.successful.NoDataException;
import com.jdar.cardsapp.models.dto.AnnulationDto;
import com.jdar.cardsapp.models.dto.PurchaseDto;
import com.jdar.cardsapp.models.dto.TransactionDto;
import com.jdar.cardsapp.models.entity.CardEntity;
import com.jdar.cardsapp.models.entity.TransactionEntity;
import com.jdar.cardsapp.models.entity.TransactionTypeEntity;
import com.jdar.cardsapp.models.mapper.Mapper;
import com.jdar.cardsapp.models.repository.ICardRepository;
import com.jdar.cardsapp.models.repository.ITransactionRepository;
import com.jdar.cardsapp.models.repository.ITransactionTypeRepository;
import com.jdar.cardsapp.services.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements ITransactionService {

    private final ITransactionTypeRepository transactionTypeRepository;
    private final ITransactionRepository transactionRepository;
    private final ICardRepository cardRepository;

    @Override
    public void saveCardTransaction(CardEntity card, TransactionTypes transactionType, BigDecimal amount) {
        try {
            TransactionTypeEntity transactionTypeEntity = transactionTypeRepository.findByTransactionTypeNameKey(transactionType.getNameKey())
                    .orElseThrow(() -> new NoDataException(String.format("TX with name key [%s] not found", transactionType.getNameKey())));

            TransactionEntity transactionEntity = TransactionEntity.builder()
                    .transactionName(transactionTypeEntity.getTransactionTypeName())
                    .transactionDate(LocalDateTime.now())
                    .transactionAmount(amount)
                    .transactionIsBlock(0)
                    .transactionType(transactionTypeEntity)
                    .transactionCard(card)
                    .build();

            transactionRepository.save(transactionEntity);
        } catch (Exception exception) {
            throw new TechnicalException(String.format("Error at TransactionServiceImpl::saveCardTransaction -> [%s]", exception.getMessage()));
        }
    }

    @Override
    @Transactional
    public TransactionDto saveBuyTransaction(PurchaseDto purchaseDto) {
        CardEntity card = cardRepository.findByCardNumber(purchaseDto.getCardId())
                .orElseThrow(() -> new NoDataException(String.format("Card with ID -> [%s] not found", purchaseDto.getCardId())));

        if (Objects.equals(card.getCardIsBlock(), 1) || Objects.equals(card.getCardIsActive(), 0)) {
            throw new NoDataException(String.format("Card [%s] is locked or is not longer active", purchaseDto.getCardId()));
        }

        try {
            if (card.getCardBalance().compareTo(new BigDecimal(purchaseDto.getPrice())) > 0) {
                TransactionTypeEntity transactionTypeEntity = transactionTypeRepository.findByTransactionTypeNameKey(TransactionTypes.PURCHASE_TX.getNameKey())
                        .orElseThrow(() -> new NoDataException(String.format("TX with name key [%s] not found", TransactionTypes.PURCHASE_TX.getNameKey())));

                BigDecimal amount = card.getCardBalance();
                amount = amount.subtract(new BigDecimal(purchaseDto.getPrice()));
                card.setCardBalance(amount);
                cardRepository.save(card);

                TransactionEntity transactionEntity = TransactionEntity.builder()
                        .transactionName(transactionTypeEntity.getTransactionTypeName())
                        .transactionDate(LocalDateTime.now())
                        .transactionAmount(new BigDecimal(purchaseDto.getPrice()))
                        .transactionIsBlock(0)
                        .transactionType(transactionTypeEntity)
                        .transactionCard(card)
                        .build();

                transactionRepository.save(transactionEntity);
                return Mapper.mapTransaction(transactionEntity);
            } else {
                throw new NoDataException(String.format("Card [%s] does not have money to complete this transaction", purchaseDto.getCardId()));
            }
        } catch (Exception exception) {
            throw new TechnicalException(String.format("Error at TransactionServiceImpl::saveBuyTransaction -> [%s]", exception.getMessage()));
        }
    }

    @Override
    @Transactional
    public TransactionDto getTransactionById(UUID transactionId) {
        TransactionEntity transactionEntity = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new NoDataException(String.format("Transaction with ID -> [%s] not found", transactionId)));

        try {
            return Mapper.mapTransaction(transactionEntity);
        } catch (Exception exception) {
            throw new TechnicalException(String.format("Error at TransactionServiceImpl::getTransactionById -> [%s]", exception.getMessage()));
        }
    }

    @Override
    @Transactional
    public String cancelTransaction(AnnulationDto annulationDto) {
        String response;

        TransactionEntity transactionEntity = transactionRepository.findById(annulationDto.getTransactionId())
                .orElseThrow(() -> new NoDataException(String.format("Transaction with ID -> [%s] not found", annulationDto.getTransactionId())));

        if (!Objects.equals(transactionEntity.getTransactionCard().getCardNumber(), annulationDto.getCardId())) {
            throw new NoDataException(String.format("Card with number [%s] does not associate to transaction [%s]", annulationDto.getCardId(), annulationDto.getTransactionId()));
        }

        if (Objects.equals(transactionEntity.getTransactionIsBlock(), 1)) {
            throw new NoDataException(String.format("Transaction [%s] is already locked", annulationDto.getTransactionId()));
        }

        try {
            LocalDate txDate = transactionEntity.getTransactionDate().toLocalDate();
            LocalDate actualDate = LocalDateTime.now().toLocalDate();
            Period period = Period.between(actualDate, txDate);

            if (period.getDays() == 0) {
                transactionEntity.setTransactionIsBlock(1);
                BigDecimal amount = transactionEntity.getTransactionCard().getCardBalance();
                amount = amount.add(transactionEntity.getTransactionAmount());
                transactionEntity.getTransactionCard().setCardBalance(amount);

                cardRepository.save(transactionEntity.getTransactionCard());
                transactionRepository.save(transactionEntity);

                response = String.format("Transacción [%s] cancelada con éxito, consulte los detalles de la cuenta con número [%s] para ver reflejada la devolución del dinero",
                        transactionEntity.getId(), transactionEntity.getTransactionCard().getCardNumber());
            } else {
                response = "No se puede cancelar la transacción ya que han pasado más de 24 horas!";
            }
        } catch (Exception exception) {
            throw new TechnicalException(String.format("Error at TransactionServiceImpl::cancelTransaction -> [%s]", exception.getMessage()));
        }

        return response;
    }

}