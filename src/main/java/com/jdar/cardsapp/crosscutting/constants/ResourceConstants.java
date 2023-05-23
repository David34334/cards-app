package com.jdar.cardsapp.crosscutting.constants;

public class ResourceConstants {

    private ResourceConstants() {
        super();
    }

    public static final String API = "/api";
    public static final String API_VERSION = "/v1";
    public static final String CARDS_ENDPOINT = "/card";
    public static final String API_CARDS = API + API_VERSION + CARDS_ENDPOINT;
    public static final String GENERATE_CARD = "/{productId}/number";
    public static final String ENROLL_CARD = "/enroll";
    public static final String PRODUCT_NUMBER = "/{cardId}";
    public static final String BALANCE = "/balance";
    public static final String GET_BALANCE = "/balance/{cardId}";
    public static final String TRANSACTION_ENDPOINT = "/transaction";
    public static final String API_TRANSACTIONS = API + API_VERSION + TRANSACTION_ENDPOINT;
    public static final String PURCHASE = "/purchase";
    public static final String TRANSACTION_ID = "/{transactionId}";
    public static final String ANNULATION = "/annulation";

}
