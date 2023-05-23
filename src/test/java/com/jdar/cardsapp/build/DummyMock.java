package com.jdar.cardsapp.build;

import com.jdar.cardsapp.models.dto.CardDto;
import com.jdar.cardsapp.models.entity.CardEntity;
import com.jdar.cardsapp.models.entity.CardTypeEntity;
import com.jdar.cardsapp.models.entity.UserEntity;

import java.math.BigDecimal;
import java.util.UUID;

public class DummyMock {

    private DummyMock() {super();}

    public static UserEntity buildUserEntity() {
        return UserEntity.builder()
                .id(UUID.randomUUID())
                .firstName("Julian D")
                .lastName("Acosta R")
                .email("juliandavid1511@gmail.com")
                .build();
    }

    public static CardTypeEntity buildCardTypeEntity() {
        return CardTypeEntity.builder()
                .id(UUID.randomUUID())
                .cardTypeCode("111000")
                .cardTypeDescription("Tarjeta Débito")
                .cardTypeNameKey("DEBIT_CARD")
                .build();
    }

    public static CardEntity buildCardEntity() {
        return CardEntity.builder()
                .id(UUID.randomUUID())
                .cardNumber("1110004483001122")
                .cardDueDate("05/2026")
                .cardIsActive(1)
                .cardIsBlock(0)
                .cardBalance(new BigDecimal(232323))
                .cardType(buildCardTypeEntity())
                .user(buildUserEntity())
                .build();
    }

    public static CardDto upBalanceBuild() {
        return CardDto.builder()
                .cardNumber("111000343434")
                .cardBalance(new BigDecimal(3400))
                .build();
    }

}
