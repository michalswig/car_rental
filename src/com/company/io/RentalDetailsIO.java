package com.company.io;

import com.company.dto.RentalDetails;
import com.company.exception.ValidationException;
import com.company.validator.Validator;

public class RentalDetailsIO {

    public static final String FIND_MAIN_MESSAGE =
            "   Find Rental Details in data base\n" +
                    "   Choose the field you will use to find Rental Details by pressing number below and enter phrase" +
                    "   You are looking to find Rental Details by:\n" +
                    "   1. ID\n" +
                    "   2. Vehicle ID\n" +
                    "   3. Customer ID\n" +
                    "   4. Employee ID\n" +
                    "   5. Pick up date\n" +
                    "   6. Drop off date\n" +
                    "   7. Total rate nett\n" +
                    "   8. Total rate gross\n" +
                    "   9. Total tax\n" +
                    "   10. Create time";

    public static final String FINAL_MESSAGE = "Car rental is properly saved in the data base";
    public static final String DROP_OFF_NOT_BEFORE_PICK_UP = "Please enter dropoff date that is after pickup date";
    public static final String PICK_UP_NOT_BEFORE_PRESENT = "Please enter pickup date that is in the future:";
    private static final String FIND_RENTAL_DETAILS_ID = "Please enter Rental details ID you are looking for";
    public static final String CORRECT_CHECK_ID = "ID is incorrect, please enter correct ID";
    public static final String CORRECT_CHECK_UPDATE_MENU = "Please enter valid menu number to move forward";
    public static final String FIND_FINAL_NEGATIVE = "Sorry, we did not find any correct match, please try again";
    public static final String FIND_FINAL_POSITIVE = "Here is the searching result:";

    public static Long getCorrectCheckIdRentalDetails() {
        boolean shouldContinue;
        Long number = null;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(RentalDetailsIO.FIND_RENTAL_DETAILS_ID);
                number = UserIO.getEnteredId();
                Validator.validateIdNumber(number);
            } catch (ValidationException | NumberFormatException e) {
                UserIO.printMessage(RentalDetailsIO.CORRECT_CHECK_ID);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return number;
    }

    public static Long getCorrectCheckIdVehicleFindRentalDetails() {
        boolean shouldContinue;
        Long number = null;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(VehicleIO.FIND_VEHICLE_ID);
                number = UserIO.getEnteredId();
                Validator.validateIdNumber(number);
            } catch (ValidationException | NumberFormatException e) {
                UserIO.printMessage(VehicleIO.CORRECT_CHECK_ID);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return number;
    }

    public static Long getCorrectCheckIdCustomerFindRentalDetails() {
        boolean shouldContinue;
        Long number = null;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(CustomerIO.FIND_CUSTOMER_ID);
                number = UserIO.getEnteredId();
                Validator.validateIdNumber(number);
            } catch (ValidationException | NumberFormatException e) {
                UserIO.printMessage(CustomerIO.CORRECT_CHECK_ID);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return number;
    }

    public static Long getCorrectCheckIdEmployeeFindRentalDetails() {
        boolean shouldContinue;
        Long number = null;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(EmployeeIO.FIND_EMPLOYEE_ID);
                number = UserIO.getEnteredId();
                Validator.validateIdNumber(number);
            } catch (ValidationException | NumberFormatException e) {
                UserIO.printMessage(EmployeeIO.CORRECT_CHECK_ID);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return number;
    }


}
