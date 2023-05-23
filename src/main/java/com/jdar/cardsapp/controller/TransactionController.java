package com.jdar.cardsapp.controller;

import com.jdar.cardsapp.crosscutting.constants.ResourceConstants;
import com.jdar.cardsapp.models.dto.AnnulationDto;
import com.jdar.cardsapp.models.dto.DataResponse;
import com.jdar.cardsapp.models.dto.PurchaseDto;
import com.jdar.cardsapp.models.dto.TransactionDto;
import com.jdar.cardsapp.services.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(ResourceConstants.API_TRANSACTIONS)
@RequiredArgsConstructor
public class TransactionController {

    private final ITransactionService transactionService;

    @PostMapping(ResourceConstants.PURCHASE)
    public DataResponse<TransactionDto> makeTransactionService(@RequestBody PurchaseDto purchaseDto) {
        return DataResponse.<TransactionDto>builder().data(transactionService.saveBuyTransaction(purchaseDto)).build();
    }

    @GetMapping(ResourceConstants.TRANSACTION_ID)
    public DataResponse<TransactionDto> getTransactionByIdService(@PathVariable UUID transactionId) {
        return DataResponse.<TransactionDto>builder().data(transactionService.getTransactionById(transactionId)).build();
    }

    @PostMapping(ResourceConstants.ANNULATION)
    public DataResponse<String> cancelTransaction(@RequestBody AnnulationDto annulationDto) {
        return DataResponse.<String>builder().data(transactionService.cancelTransaction(annulationDto)).build();
    }

}
