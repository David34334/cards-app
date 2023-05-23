package com.jdar.cardsapp.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class CardTypeDto {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("card_type_code")
    private String cardTypeCode;

    @JsonProperty("card_type_description")
    private String cardTypeDescription;

}
