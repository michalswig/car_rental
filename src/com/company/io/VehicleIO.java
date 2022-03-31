package com.company.io;

import com.company.dto.Vehicle;
import com.company.exception.RemoveDateException;
import com.company.exception.ValidationException;
import com.company.exception.VehicleNotFoundException;
import com.company.service.VehicleService;
import com.company.validator.Validator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static com.company.io.UserIO.parseToLocalDate;

public class VehicleIO {

    public static final String UPDATE_MAIN_MESSAGE =
            "What field do you wish to change?\n" +
                    "    1. Registration Number\n" +
                    "    2. Brand\n" +
                    "    3. Model\n" +
                    "    4. VIN Number\n" +
                    "    5. Current Rate Nett\n" +
                    "    6. Current Tax Level\n";

    public static final String FIND_MAIN_MESSAGE =
            "   Find Vehicle in data base\n" +
                    "   Choose the field you will use to find Vehicle by pressing number with the phrase you are looking for:\n" +
                    "    1. by ID\n" +
                    "    2. by Registration Number\n" +
                    "    3. by Brand\n" +
                    "    4. by Model\n" +
                    "    5. by VIN Number\n" +
                    "    6. by Current Rate Nett\n" +
                    "    7. by Current Rate Gross\n" +
                    "    8. by Current Tax Level\n" +
                    "    9. by Current Rate Gross\n";

    private static final String UPDATE_REGISTRATION_NUMBER = "Please enter Vehicle`s new Registration Number, use letters and numbers only:";
    private static final String UPDATE_BRAND = "Please enter Vehicle`s new Brand, use letters and numbers only:";
    private static final String UPDATE_MODEL = "Please enter Vehicle`s new Model, use letters and numbers only:";
    private static final String UPDATE_VIN_NUMBER = "Please enter Vehicle`s new Vin Number, 17 digits and letters";
    private static final String UPDATE_CURRENT_RATE_NETT = "Please enter Vehicle`s current rate nett, use digits only";
    private static final String UPDATE_CURRENT_TAX_LEVEL = "Please enter Vehicle`s tax level, use digits only";
    public static final String SHOW_ALL_VEHICLES_MESSAGE = "Here is the list of all vehicles:";

    public static final String GET_ID_MESSAGE = "Choose vehicleID to rent:";
    public static final String PICK_UP_DATE_MESSAGE = "Choose pick-up date dd/mm/yyyy";
    public static final String DROP_OFF_DATE_MESSAGE = "Choose drop-off date dd/mm/yyyy";
    public static final String GET_INITIAL_MESSAGE = "Enter ID of Vehicle you wish to display";
    public static final String GET_FINAL_MESSAGE = "Thank you.";
    public static final String NEGATIVE_FIND = "Sorry, we did not find any correct match, try again";
    public static final String NEGATIVE_UPDATE = "Sorry, we did not properly updated the Vehicle, try again";
    public static final String FINAL_CREATE_MESSAGE = "Vehicle is properly loaded into the system";
    public static final String FINAL_UPDATE_MESSAGE = "Vehicle is properly updated in the system";

    public static final String CREATE_INITIAL_MESSAGE = "Start to enter data of a new Vehicle:";

    public static final String PROVIDE_REGISTRATION_NUMBER = "Please enter new Vehicle`s Registration Number, use letters and digits only:";
    public static final String PROVIDE_BRAND = "Please enter new Vehicle`s Brand, use letters and digits only:";
    public static final String PROVIDE_MODEL = "Please enter new Vehicle`s Model, use letters and digits only:";
    public static final String PROVIDE_VIN_NUMBER = "Please enter new Vehicle`s Vin Number, use letters digits only:";
    public static final String PROVIDE_RATE_NETT = "Please enter new Vehicle`s Rate Nett, use digits only:";

    private static final String CORRECT_CHECK_REGISTRATION_NUMBER = "Registration Number is incorrect, please enter correct Registration Number";
    private static final String CORRECT_CHECK_BRAND = "Brand is incorrect, please enter correct Brand";
    private static final String CORRECT_CHECK_MODEL = "Model is incorrect, please enter correct Model";
    private static final String CORRECT_CHECK_VIN_NUMBER = "Vin Number is incorrect, please enter correct Vin Number";
    public static final String CORRECT_CHECK_RATE_NETT = "Rate Nett is incorrect, please enter correct Rate Nett";
    private static final String CORRECT_CHECK_RATE_GROSS = "Rate Gross is incorrect, please enter correct Rate Nett";
    private static final String CORRECT_CHECK_TAX_LEVEL = "Tax level is incorrect, please enter correct tax level";

    public static final String CORRECT_CHECK_UPDATE_MENU = "Please enter valid menu number to move forward";

    public static final String FIND_VEHICLE_ID = "Please enter Vehicle`s ID you are looking for";
    private static final String FIND_VEHICLE_REGISTRATION_NUMBER = "Please enter Vehicle`s Registration Number you are looking for";
    private static final String FIND_VEHICLE_BRAND = "Please enter Vehicle`s Brand you are looking for";
    private static final String FIND_VEHICLE_MODEL = "Please enter Vehicle`s Model you are looking for";
    private static final String FIND_VEHICLE_VIN_NUMBER = "Please enter Vehicle`s VIN Number you are looking for";
    private static final String FIND_VEHICLE_RATE_NETT = "Please enter Vehicle`s current Rate Nett you are looking for";
    private static final String FIND_VEHICLE_RATE_GROSS = "Please enter Vehicle`s current Rate Gross you are looking for";
    private static final String FIND_VEHICLE_TAX_LEVEL = "Please enter Vehicle`s Tax Level you are looking for";
    private static final String FIND_VEHICLE_DEACTIVATION_DATE = "Please enter Vehicle`s Deactivation Date you are looking for";
    public static final String FIND_FINAL_NEGATIVE = "Sorry, we did not find any correct match, please try again";
    public static final String FIND_FINAL = "Here are the chosen Vehicles:";
    public static final String UPDATE_INITIAL_MESSAGE = "Enter Vehicle ID you wish to update";
    public static final String DEACTIVATE_INITIAL_MESSAGE = "Enter ID of Vehicle you wish to deactivate";
    public static final String DEACTIVATE_FINAL_MESSAGE = "Vehicle is deactivate";
    public static final String DEACTIVATE_NOT_ACTIVE_MESSAGE = "Vehicle is already not active.";

    public static final String PROVIDE_ID = "Please enter new Vehicle`s ID";
    public static final String CORRECT_CHECK_ID = "ID is incorrect, please try again";
    public static final String CORRECT_CHECK_REMOVE_DATE = "The vehicle you have chosen is no longer available, please try again";

    public static Long getCorrectCheckIdFind() {
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

    public static String getCorrectCheckRegistrationNumberFind() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(VehicleIO.FIND_VEHICLE_REGISTRATION_NUMBER);
                text = UserIO.getEnteredText();
                Validator.validateVehicleRegistrationNumber(text);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_REGISTRATION_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static String getCorrectCheckBrandFind() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(VehicleIO.FIND_VEHICLE_BRAND);
                text = UserIO.getEnteredText();
                Validator.validateText(text);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_BRAND);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static String getCorrectCheckModelFind() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(VehicleIO.FIND_VEHICLE_MODEL);
                text = UserIO.getEnteredText();
                Validator.validateDigitsLetters(text);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_MODEL);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static String getCorrectCheckVinNumberFind() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(VehicleIO.FIND_VEHICLE_VIN_NUMBER);
                text = UserIO.getEnteredText();
                Validator.validateVinNumber(text);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_VIN_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static BigDecimal getCorrectCheckCurrentRateNettFind() {
        boolean shouldContinue;
        BigDecimal rate = BigDecimal.ZERO;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(VehicleIO.FIND_VEHICLE_RATE_NETT);
                rate = new BigDecimal(UserIO.getEnteredText());
                Validator.validateMoney(rate);
            } catch (ValidationException | NumberFormatException e) {
                UserIO.printMessage(CORRECT_CHECK_RATE_NETT);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return rate;
    }

    public static BigDecimal getCorrectCheckCurrentRateGrossFind() {
        boolean shouldContinue;
        BigDecimal rate = BigDecimal.ZERO;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(VehicleIO.FIND_VEHICLE_RATE_GROSS);
                rate = new BigDecimal(UserIO.getEnteredText());
                Validator.validateMoney(rate);
            } catch (ValidationException | NumberFormatException e) {
                UserIO.printMessage(CORRECT_CHECK_RATE_GROSS);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return rate;
    }

    public static BigDecimal getCorrectCheckCurrentTaxPercentFind() {
        boolean shouldContinue;
        BigDecimal rate = BigDecimal.ZERO;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(VehicleIO.FIND_VEHICLE_TAX_LEVEL);
                rate = new BigDecimal(UserIO.getEnteredText());
                Validator.validateMoney(rate);
            } catch (ValidationException | NumberFormatException e) {
                UserIO.printMessage(CORRECT_CHECK_TAX_LEVEL);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return rate;
    }

    public static Long getCorrectCheckId() {
        boolean shouldContinue;
        Long number = null;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(VehicleIO.PROVIDE_ID);
                number = UserIO.getEnteredId();
                Validator.validateIdNumber(number);
            } catch (ValidationException e) {
                UserIO.printMessage(VehicleIO.CORRECT_CHECK_ID);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return number;
    }

    public static String getCorrectCheckRegistrationNumber() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_REGISTRATION_NUMBER);
                text = UserIO.getEnteredText();
                Validator.validateVehicleRegistrationNumber(text);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_REGISTRATION_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static String getCorrectCheckBrand() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_BRAND);
                text = UserIO.getEnteredText();
                Validator.validateText(text);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_BRAND);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static String getCorrectCheckModel() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_MODEL);
                text = UserIO.getEnteredText();
                Validator.validateDigitsLetters(text);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_MODEL);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static String getCorrectCheckVinNumber() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_VIN_NUMBER);
                text = UserIO.getEnteredText();
                Validator.validateVinNumber(text);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_VIN_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static BigDecimal getCorrectCheckCurrentRateNett() {
        boolean shouldContinue;
        BigDecimal rate = BigDecimal.ZERO;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_RATE_NETT);
                rate = new BigDecimal(UserIO.getEnteredText()).setScale(2, RoundingMode.HALF_UP);
                Validator.validateMoney(rate);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_RATE_NETT);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return rate;
    }

    public static String getCorrectCheckUpdateRegistrationNumber() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(UPDATE_REGISTRATION_NUMBER);
                text = UserIO.getEnteredText();
                Validator.validateVehicleRegistrationNumber(text);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_REGISTRATION_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static String getCorrectCheckUpdateBrand() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(UPDATE_BRAND);
                text = UserIO.getEnteredText();
                Validator.validateText(text);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_BRAND);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static String getCorrectCheckUpdateModel() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(UPDATE_MODEL);
                text = UserIO.getEnteredText();
                Validator.validateText(text);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_MODEL);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static String getCorrectCheckUpdateVinNumber() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(UPDATE_VIN_NUMBER);
                text = UserIO.getEnteredText();
                Validator.validateVinNumber(text);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_VIN_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static BigDecimal getCorrectCheckUpdateCurrentRateNett() {
        boolean shouldContinue;
        BigDecimal rate = BigDecimal.ZERO;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(UPDATE_CURRENT_RATE_NETT);
                rate = new BigDecimal(UserIO.getEnteredText()).setScale(2, RoundingMode.HALF_UP);
                Validator.validateMoney(rate);
            } catch (ValidationException | NumberFormatException e) {
                UserIO.printMessage(CORRECT_CHECK_RATE_NETT);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return rate;
    }

    public static BigDecimal getCorrectCheckUpdateTaxLevel() {
        boolean shouldContinue;
        BigDecimal tax = BigDecimal.ZERO;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(UPDATE_CURRENT_TAX_LEVEL);
                tax = new BigDecimal(UserIO.getEnteredText());
                Validator.validateMoney(tax);
            } catch (ValidationException | NumberFormatException e) {
                UserIO.printMessage(CORRECT_CHECK_TAX_LEVEL);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return tax;
    }

    public static Long getCorrectCheckIdVehicle(VehicleService vehicleService) {
        boolean shouldContinue;
        Long number = null;
        do {
            shouldContinue = false;
            try {
                number = UserIO.getEnteredId();
                Validator.validateIdNumber(number);
                checkIfExistsInDBVehicle(vehicleService, number);
                checkVehicleRemoveDate(vehicleService, number);
            } catch (ValidationException | VehicleNotFoundException | NumberFormatException e) {
                UserIO.printMessage(VehicleIO.CORRECT_CHECK_ID);
                shouldContinue = true;
            } catch (RemoveDateException e) {
                UserIO.printMessage(VehicleIO.CORRECT_CHECK_REMOVE_DATE);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return number;
    }

    private static void checkIfExistsInDBVehicle(VehicleService vehicleService, Long id) {
        if (vehicleService.get(id) == null) throw new VehicleNotFoundException();
    }

    private static void checkVehicleRemoveDate(VehicleService vehicleService, Long id) {
        Vehicle vehicle = vehicleService.get(id);
        if (vehicle.getRemoveDateTime() != null) throw new RemoveDateException();
    }

    public static Long getCorrectCheckIdVehicleUpdate(VehicleService vehicleService) {
        boolean shouldContinue;
        Long number = null;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(VehicleIO.PROVIDE_ID);
                number = UserIO.getEnteredId();
                Validator.validateIdNumber(number);
                checkIfExistsInDBVehicle(vehicleService, number);
            } catch (ValidationException | VehicleNotFoundException | NumberFormatException e) {
                UserIO.printMessage(VehicleIO.CORRECT_CHECK_ID);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return number;
    }

    public static LocalDate getCorrectCheckDate() {
        boolean shouldContinue;
        LocalDate date = null;
        do {
            shouldContinue = false;
            try {
                date = parseToLocalDate(UserIO.getEnteredText());
            } catch (DateTimeParseException e) {
                UserIO.printMessage(UserIO.CORRECT_CHECK_DATE);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return date;
    }

}
