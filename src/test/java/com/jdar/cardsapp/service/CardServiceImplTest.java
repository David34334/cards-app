package com.jdar.cardsapp.service;

import com.jdar.cardsapp.build.DummyMock;
import com.jdar.cardsapp.crosscutting.constants.TransactionTypes;
import com.jdar.cardsapp.models.dto.CardDto;
import com.jdar.cardsapp.models.entity.CardEntity;
import com.jdar.cardsapp.models.mapper.Mapper;
import com.jdar.cardsapp.models.repository.ICardRepository;
import com.jdar.cardsapp.models.repository.ICardTypeRepository;
import com.jdar.cardsapp.models.repository.IUserRepository;
import com.jdar.cardsapp.services.ICardService;
import com.jdar.cardsapp.services.ITransactionService;
import com.jdar.cardsapp.services.impl.CardServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class CardServiceImplTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private ICardRepository cardRepository;

    @Mock
    private ICardTypeRepository cardTypeRepository;

    @Mock
    private ITransactionService transactionService;

    @InjectMocks
    private CardServiceImpl cardService;

    @Test
    void createCardServiceSuccessTest() {
        Mockito.when(userRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(DummyMock.buildUserEntity()));
        Mockito.when(cardTypeRepository.findByCardTypeCode(Mockito.anyString())).thenReturn(Optional.of(DummyMock.buildCardTypeEntity()));
        Mockito.when(cardRepository.save(Mockito.any(CardEntity.class))).thenReturn(DummyMock.buildCardEntity());

        CardDto cardDto = cardService.createCardService("1110004483001122", UUID.randomUUID());

        Assertions.assertNotNull(cardDto);
    }

    @Test
    void enrollCardServiceSuccessTest() {
        CardEntity card = DummyMock.buildCardEntity();
        card.setCardIsActive(0);

        Mockito.when(cardRepository.findByCardNumber(Mockito.anyString())).thenReturn(Optional.of(card));
        Mockito.when(cardRepository.save(Mockito.any(CardEntity.class))).thenReturn(DummyMock.buildCardEntity());

        CardDto cardDto = cardService.enrollCardService(Mapper.mapCardEntityToDto(DummyMock.buildCardEntity()));

        Assertions.assertNotNull(cardDto);
        Assertions.assertEquals(1, cardDto.getCardIsActive());
    }

    @Test
    void lockCardServiceSuccessTest() {
        CardEntity card = DummyMock.buildCardEntity();
        card.setCardIsBlock(1);

        Mockito.when(cardRepository.findByCardNumber(Mockito.anyString())).thenReturn(Optional.of(DummyMock.buildCardEntity()));
        Mockito.when(cardRepository.save(Mockito.any(CardEntity.class))).thenReturn(card);

        CardDto cardDto = cardService.lockCardService("111000343434");

        Assertions.assertNotNull(cardDto);
        Assertions.assertEquals(1, cardDto.getCardIsBlock());
    }

    @Test
    void upBalanceServiceSuccessTest() {
        CardDto cardDto = DummyMock.upBalanceBuild();
        CardEntity card = DummyMock.buildCardEntity();
        BigDecimal amount = card.getCardBalance();
        amount.add(cardDto.getCardBalance());
        card.setCardBalance(amount);

        Mockito.when(cardRepository.findByCardNumber(Mockito.anyString())).thenReturn(Optional.of(DummyMock.buildCardEntity()));
        Mockito.when(cardRepository.save(Mockito.any(CardEntity.class))).thenReturn(card);

        CardDto cardResponse = cardService.upBalanceService(cardDto);

        Assertions.assertNotNull(cardResponse);
        Assertions.assertEquals(card.getCardBalance(), cardResponse.getCardBalance());
    }

    @Test
    void getCardBalanceServiceSuccessTest() {
        Mockito.when(cardRepository.findByCardNumber(Mockito.anyString())).thenReturn(Optional.of(DummyMock.buildCardEntity()));
        String response = cardService.getCardBalanceService("343434");

        Assertions.assertNotNull(response);
    }

}
