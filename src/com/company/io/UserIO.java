package com.company.io;

import com.company.exception.ValidationException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UserIO {

    private static final Scanner scanner = new Scanner(System.in);

    public static final String WELCOME_MESSAGE_MAIN =
            """
                      
                    ***Welcome to car rental application***
                       Choose an option by pressing number below:
                       1. Create Customer
                       2. Update Customer
                       3. Find Customer
                       4. Deactivate Customer
                       5. Create Vehicle
                       6. Update Vehicle
                       7. Find Vehicle
                       8. Deactivate Vehicle
                       9. Create an Employee
                       10. Update an Employee
                       11. Find an Employee
                       12. Deactivate an Employee
                       13. Create Rental
                       14. Find Rental
                       15. Find Vehicle Rates Changes 
                       16. Top Earners Vehicle by month report
                       17. Top Earners Vehicle by year report
                       18. Top Earners Vehicle by rented days report
                       19. Exit the application""";

    public static final String FINAL_MESSAGE_MAIN = "Application is closed, thank you.";
    public static final String FINAL_NEGATIVE_MESSAGE_MAIN = "You entered wrong number, please try again.";
    private static final String FIND_DATE = "Please enter the date, format dd/mm/yyy";
    private static final String FIND_BIG_DECIMAL = "Please enter the money value";
    public static final String FIND_NEGATIVE = "We did not find a record based on given data.";
    public static final String CORRECT_CHECK_DATE = "Date is incorrect, please try again, format dd/mm/yyy";
    public static final String CORRECT_NUMBER = "This is not a valid menu choice, please try again";
    public static final String CORRECT_BIG_DECIMAL = "This is not a valid money value, please try again";
    public static final String DATA_BASE_ERROR = "error connecting database ";
    public static final String PARSE_ERROR = "please, reenter date, could be in the wrong format or contact the administrator ";
    public static final String FIND_FINAL_NEGATIVE = "Sorry, we did not find any correct match, please try again";
    public static final String TOP_EARNERS_REPORT_MONTH = "Here are the top earners by month:";
    public static final String TOP_EARNERS_REPORT_YEAR = "Here are the top earners by year:";
    public static final String TOP_EARNERS_REPORT_RENTED_DAYS = "Here are the top earners by rented days:";

    public static String getEnteredText() {
        return scanner.nextLine();
    }

    public static Long getEnteredId() {
        return Long.valueOf(scanner.nextLine());
    }

    public static Integer getEnteredNumber() {
        return Integer.parseInt(scanner.nextLine());
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static BigDecimal getCorrectCheckCurrentRate() {
        boolean shouldContinue;
        BigDecimal rate = BigDecimal.ZERO;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(UserIO.FIND_BIG_DECIMAL);
                rate = new BigDecimal(UserIO.getEnteredText()).setScale(2, RoundingMode.HALF_UP);
            } catch (ValidationException | NumberFormatException e) {
                UserIO.printMessage(UserIO.CORRECT_BIG_DECIMAL);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return rate;
    }

    public static LocalDate getCorrectCheckDate() {
        boolean shouldContinue;
        LocalDate date = null;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(UserIO.FIND_DATE);
                date = parseToLocalDate(UserIO.getEnteredText());
            } catch (DateTimeParseException e) {
                UserIO.printMessage(UserIO.CORRECT_CHECK_DATE);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return date;
    }

    public static LocalDate parseToLocalDate(String userInputText) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(userInputText, formatter);
    }

}
