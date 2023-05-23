package com.jdar.cardsapp.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class TransactionDto {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("transaction_name")
    private String transactionName;

    @JsonProperty("transaction_date")
    private LocalDateTime transactionDate;

    @JsonProperty("transaction_amount")
    private BigDecimal transactionAmount;

    @JsonProperty("transaction_is_block")
    private int transactionIsBlock;

    @JsonProperty("transaction_type")
    private TransactionTypeDto transactionType;

    @JsonProperty("transaction_card")
    private CardDto transactionCard;

}
