package com.jdar.cardsapp.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
public class CardDto {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("card_number")
    private String cardNumber;

    @JsonProperty("card_due_date")
    private String cardDueDate;

    @JsonProperty("card_is_active")
    private int cardIsActive;

    @JsonProperty("card_is_block")
    private int cardIsBlock;

    @JsonProperty("card_balance")
    private BigDecimal cardBalance;

    @JsonProperty("card_type")
    private CardTypeDto cardType;

    @JsonProperty("user")
    private UserDto user;

}
