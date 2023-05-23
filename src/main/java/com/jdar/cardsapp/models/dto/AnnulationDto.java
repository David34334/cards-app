package com.jdar.cardsapp.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class AnnulationDto {

    @JsonProperty("card_id")
    private String cardId;

    @JsonProperty("transaction_id")
    private UUID transactionId;

}
