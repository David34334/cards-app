package com.jdar.cardsapp.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class TransactionTypeDto {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("transaction_type_name")
    private String transactionTypeName;

    @JsonProperty("transaction_type_description")
    private String transactionTypeDescription;

    @JsonProperty("transaction_type_name_key")
    private String transactionTypeNameKey;

}
