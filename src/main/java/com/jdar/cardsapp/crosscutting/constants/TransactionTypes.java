package com.jdar.cardsapp.crosscutting.constants;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Getter
public enum TransactionTypes {

    LOCK_CARD_TX("Bloqueo de tarjeta", "LOCK_CARD"),
    UNLOCK_CARD_TX("Desbloqueo de tarjeta", "UNLOCK_CARD"),
    ACTIVATION_CARD_TX("Activación de tarjeta", "ACTIVATION_CARD"),
    PURCHASE_TX("Compra con tarjeta", "PURCHASE_TRANSACTION"),
    CREATION_CARD_TX("Creación de tarjeta", "CREATION_CARD"),
    UP_BALANCE_TX("Recarga de saldo", "BALANCE_UP_CARD");

    private final String label;
    private final String nameKey;

    TransactionTypes(String label, String nameKey) {
        this.label = label;
        this.nameKey = nameKey;
    }

    public static Optional<TransactionTypes> findByNameKey(String nameKey) {
        return Arrays.stream(values()).filter(transaction -> Objects.equals(transaction.nameKey, nameKey)).findFirst();
    }

}
