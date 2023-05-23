package com.jdar.cardsapp.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PurchaseDto {

    @JsonProperty("card_id")
    private String cardId;

    @JsonProperty("price")
    private String price;

}