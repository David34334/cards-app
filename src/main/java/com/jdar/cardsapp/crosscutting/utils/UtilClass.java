package com.jdar.cardsapp.crosscutting.utils;

import com.jdar.cardsapp.crosscutting.constants.Constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UtilClass {

    private UtilClass() {super();}

    public static String generateRandomNumber() {
        return String.valueOf((long) Math.floor(Math.random() * 9000000000L) + 1000000000L);
    }

    public static String generateDueDate() {
        return LocalDate.now().plusYears(Constants.PLUS_YEARS).format(DateTimeFormatter.ofPattern(Constants.FORMAT_DATE));
    }

}
