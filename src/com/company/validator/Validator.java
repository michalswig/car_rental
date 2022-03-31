package com.company.validator;

import com.company.exception.ValidationException;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class Validator {

    private final static Pattern PATTERN_TEXT = Pattern.compile("[A-Za-zĄąĆćĘęŁłŃńÓóŚśŹźŻż _-]+");
    private final static Pattern PATTERN_NUMBER = Pattern.compile("^[0-9]+$");
    private final static Pattern PATTERN_BIG_DECIMAL = Pattern.compile("^(\\d*\\.)?\\d+$");
    private final static Pattern PATTERN_VIN_NUMBER = Pattern.compile("^\\b[(A-H|J-N|P|R-Z|0-9)]{17}\\b$");
    private final static Pattern PATTERN_PERSONAL_ID = Pattern.compile("[0-9]{4}[0-3][0-9]{1}[0-9]{5}");
    private final static Pattern PATTERN_POSTAL_CODE_NUMBER = Pattern.compile("^[0-9]{2}-[0-9]{3}$");
    private final static Pattern PATTERN_NIP_NUMBER = Pattern.compile("^((\\d{3}[- ]\\d{3}[- ]\\d{2}[- ]\\d{2})|(\\d{3}[- ]\\d{2}[- ]\\d{2}[- ]\\d{3}))$");
    private final static Pattern PATTERN_VEHICLE_REGISTRATION_NUMBER = Pattern.compile("[A-Za-zĄąĆćĘęŁłŃńÓóŚśŹźŻż0-9 _-]+");
    private final static Pattern PATTERN_DIGITS_LETTERS = Pattern.compile("[A-Za-zĄąĆćĘęŁłŃńÓóŚśŹźŻż0-9 _-]+");

    public static void validateText(String text) {
        if (text == null || !(PATTERN_TEXT.matcher(text).matches())) {
            throw new ValidationException();
        }
    }

    public static void validateDigitsLetters(String text) {
        if (text == null || !(PATTERN_DIGITS_LETTERS.matcher(text).matches())) {
            throw new ValidationException();
        }
    }

    public static void validateVehicleRegistrationNumber(String text) {
        if (text == null || !(PATTERN_VEHICLE_REGISTRATION_NUMBER.matcher(text).matches())) {
            throw new ValidationException();
        }
    }

    public static void validateNumber(Integer number) {
        if (number == null || !(PATTERN_NUMBER.matcher(String.valueOf(number)).matches())) {
            throw new ValidationException();
        }
    }

    public static void validateIdNumber(Long number) {
        if (number == null || !(PATTERN_NUMBER.matcher(String.valueOf(number)).matches())) {
            throw new ValidationException();
        }
    }

    public static void validatePersonalId(String number) {
        if (number == null || !(PATTERN_PERSONAL_ID.matcher(number).matches())) {
            throw new ValidationException();
        }
    }

    public static void validateMoney(BigDecimal rate) {
        if (rate == null || !(PATTERN_BIG_DECIMAL.matcher(String.valueOf(rate)).matches())) {
            throw new ValidationException();
        }
    }

    public static void validateVinNumber(String number) {
        if (number == null || !(PATTERN_VIN_NUMBER.matcher(number).matches())) {
            throw new ValidationException();
        }
    }

    public static void validatePostalCodeNumber(String number) {
        if (number == null || !(PATTERN_POSTAL_CODE_NUMBER.matcher(number).matches())) {
            throw new ValidationException();
        }
    }

    public static void validateNIPNumber(String number) {
        if (number == null || !(PATTERN_NIP_NUMBER.matcher(number).matches())) {
            throw new ValidationException();
        }
    }

}
