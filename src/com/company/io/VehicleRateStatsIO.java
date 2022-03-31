package com.company.io;

import com.company.exception.ValidationException;
import com.company.dto.VehicleRateStats;
import com.company.validator.Validator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class VehicleRateStatsIO {

    private static final Scanner scanner = new Scanner(System.in);

    public static final String FIND_MAIN_MESSAGE =
            "   Find Vehicle Rate Stats in data base\n" +
                    "   Choose the field you will use to find ehicle Rate Stats by pressing number with the phrase you are looking for:\n" +
                    "    1. by ID\n" +
                    "    2. by vehicle ID\n" +
                    "    3. by rate Change Date\n" +
                    "    4. by rate nett before change\n" +
                    "    5. by rate gross before change\n" +
                    "    6. by tax percent before change\n";

    public static final String PROVIDE_ID = "Please enter Vehicle Rate Stats ID";
    public static final String CORRECT_CHECK_ID = "ID is incorrect, please try again";
    public static final String FIND_FINAL = "Here are the chosen rate changes:";
    public static final String FIND_FINAL_NEGATIVE = "Sorry, we did not find any correct match, please try again";
    public static final String FIND_VEHICLE_ID = "Please enter Vehicle`s ID you are looking for";
    private static final String FIND_CHECK_DATE = "Please enter the date you in format dd/mm/yyy";
    private static final String CORRECT_CHECK_DATE = "Date is incorrect, please try again, format dd/mm/yyy";
    public static final String CORRECT_BIG_DECIMAL = "This is not a valid money value, please try again";
    public static final String PROVIDE_BIG_DECIMAL = "Please enter money value.";
    public static final String SHOW_ALL_CHANGES_MESSAGE = "Here is the list of all rate changes:";

    public static Long getCorrectCheckIdFindVehicleRateStats() {
        boolean shouldContinue;
        Long number = null;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(VehicleRateStatsIO.PROVIDE_ID);
                number = UserIO.getEnteredId();
                Validator.validateIdNumber(number);
            } catch (ValidationException | NumberFormatException e) {
                UserIO.printMessage(VehicleRateStatsIO.CORRECT_CHECK_ID);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return number;
    }

    public static Long getCorrectCheckVehicleIdFindVehicleRateStats() {
        boolean shouldContinue;
        Long number = null;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(VehicleRateStatsIO.FIND_VEHICLE_ID);
                number = UserIO.getEnteredId();
                Validator.validateIdNumber(number);
            } catch (ValidationException | NumberFormatException e) {
                UserIO.printMessage(VehicleRateStatsIO.CORRECT_CHECK_ID);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return number;
    }

    public static LocalDate getCorrectCheckRateChangeDateFindVehicleRateStats() {
        boolean shouldContinue;
        LocalDate date = null;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(VehicleRateStatsIO.FIND_CHECK_DATE);
                date = UserIO.parseToLocalDate(UserIO.getEnteredText());
            } catch (DateTimeParseException e) {
                UserIO.printMessage(VehicleRateStatsIO.CORRECT_CHECK_DATE);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return date;
    }

    public static BigDecimal getCorrectCheckRateFindVehicleRateStats() {
        boolean shouldContinue;
        BigDecimal value = null;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(VehicleRateStatsIO.PROVIDE_BIG_DECIMAL);
                value = BigDecimal.valueOf(Long.parseLong(scanner.nextLine()));
            } catch (IllegalArgumentException e) {
                UserIO.printMessage(VehicleRateStatsIO.CORRECT_BIG_DECIMAL);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return value;
    }

}
