package com.jdar.cardsapp.controller;

import com.jdar.cardsapp.crosscutting.constants.ResourceConstants;
import com.jdar.cardsapp.models.dto.CardDto;
import com.jdar.cardsapp.models.dto.DataResponse;
import com.jdar.cardsapp.services.ICardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(ResourceConstants.API_CARDS)
@RequiredArgsConstructor
public class CardController {

    private final ICardService cardService;

    @GetMapping(ResourceConstants.GENERATE_CARD)
    public DataResponse<CardDto> saveCardService(@PathVariable String productId, @RequestParam(name = "user_id") UUID userId) {
        return DataResponse.<CardDto>builder().data(cardService.createCardService(productId, userId)).build();
    }

    @PostMapping(ResourceConstants.ENROLL_CARD)
    public DataResponse<CardDto> enrollCardService(@RequestBody CardDto card) {
        return DataResponse.<CardDto>builder().data(cardService.enrollCardService(card)).build();
    }

    @DeleteMapping(ResourceConstants.PRODUCT_NUMBER)
    public DataResponse<CardDto> lockCardService(@PathVariable String cardId) {
        return DataResponse.<CardDto>builder().data(cardService.lockCardService(cardId)).build();
    }

    @PostMapping(ResourceConstants.BALANCE)
    public DataResponse<CardDto> upBalanceService(@RequestBody CardDto cardDto) {
        return DataResponse.<CardDto>builder().data(cardService.upBalanceService(cardDto)).build();
    }

    @GetMapping(ResourceConstants.GET_BALANCE)
    public DataResponse<String> getCardBalanceService(@PathVariable String cardId) {
        return DataResponse.<String>builder().data(cardService.getCardBalanceService(cardId)).build();
    }

}
