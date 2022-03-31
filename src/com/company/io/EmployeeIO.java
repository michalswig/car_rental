package com.company.io;

import com.company.dto.Employee;
import com.company.exception.EmployeeNotFoundException;
import com.company.exception.RemoveDateException;
import com.company.exception.ValidationException;
import com.company.service.EmployeeService;
import com.company.validator.Validator;

public class EmployeeIO {

    public static final String UPDATE_MAIN_MESSAGE =
            "What field do you wish to change?\n" +
                    "    1. FirstName\n" +
                    "    2. LastName\n" +
                    "    3. PersonalIdNumber\n" +
                    "    4. City\n" +
                    "    5. PostalCode\n" +
                    "    6. Street Name\n" +
                    "    7. Street Number\n" +
                    "    8. Street Apartment Number";


    public static final String FIND_MAIN_MESSAGE =
            "   Find Employee in data base\n" +
                    "   Choose the field you will use to find Employee by pressing number with the phrase you are looking for:\n" +
                    "    1. by ID\n" +
                    "    2. by FirstName\n" +
                    "    3. by LastName\n" +
                    "    4. by PersonalIdNumber\n" +
                    "    5. by City\n" +
                    "    6. by PostalCode\n" +
                    "    7. by Street Name\n" +
                    "    8. by Street Number\n" +
                    "    9. by Street Apartment Number\n" +
                    "    10. by Deactivation Date dd/mm/yyy";

    public static final String CORRECT_CHECK_UPDATE_MENU = "Please enter valid menu number to move forward";
    public static final String SHOW_ALL_EMPLOYEES_MESSAGE = "Here is the list of all employees:";
    private static final String CHANGE_FIRST_NAME = "Please enter new Employee`s First Name, use letters only:";
    private static final String CHANGE_LAST_NAME = "Please enter new Employee`s Last Name, use letters only:";
    private static final String CHANGE_PERSONAL_ID_NUMBER = "Please enter new Employee`s Personal ID Number, use digits only (max 25 digits):";
    private static final String CHANGE_CITY = "Please enter new Employee`s City of residence, use letters only:";
    private static final String CHANGE_POSTAL_CODE = "Please enter new Employee`s postal code, use digits and only xx-xxx:";
    private static final String CHANGE_STREET = "Please enter new Employee`s street, use letters and digits only";
    private static final String CHANGE_STREET_NUMBER = "Please enter new Employee`s street number, use digits only";
    private static final String CHANGE_APARTMENT_NUMBER = "Please enter new Employee`s apartment number, use digits only";

    public static final String CORRECT_CHECK_ID = "ID is incorrect, please enter correct ID";
    private static final String CORRECT_CHECK_FIRST_NAME = "First name is incorrect, please enter correct first name";
    private static final String CORRECT_CHECK_LAST_NAME = "Last name is incorrect, please enter correct last name";
    private static final String CORRECT_CHECK_PERSONAL_ID_NUMBER = "Personal ID number is incorrect, please enter correct Personal ID number";
    private static final String CORRECT_CHECK_CITY = "City is incorrect, please enter correct City";
    private static final String CORRECT_CHECK_POSTAL_CODE = "Postal Code number is incorrect, please enter correct Postal Code number";
    private static final String CORRECT_CHECK_STREET = "Street is incorrect, please enter correct street";
    private static final String CORRECT_CHECK_STREET_NUMBER = "Street number is incorrect, please enter correct street number";
    private static final String CORRECT_CHECK_APARTMENT_NUMBER = "Apartment number is incorrect, please enter correct apartment number";

    public static final String UPDATE_FINAL_MESSAGE = "Employee is properly updated in the the data base";
    public static final String UPDATE_NEGATIVE_MESSAGE = "Employee was not properly updated in the the system, please try again";

    public static final String GET_ID_MESSAGE = "Good morning, what is your Employee ID?";
    public static final String FIND_NEGATIVE_MESSAGE = "Sorry, no match for this employee, try again";

    public static final String UPDATE_INITIAL_MESSAGE = "Enter ID of Employee you wish to update:";

    public static final String CREATE_INITIAL_MESSAGE = "Start to enter data of a new Employee:";

    public static final String PROVIDE_ID = "Please enter new Employee`s ID, use digits only:";
    private static final String PROVIDE_FIRST_NAME = "Please enter new Employee`s First Name, use letters only:";
    private static final String PROVIDE_LAST_NAME = "Please enter new Employee`s Last Name, use letters only:";
    private static final String PROVIDE_PERSONAL_ID_NUMBER = "Please enter new Employee`s Personal ID Number, use digits only (max 25 digits):";
    private static final String PROVIDE_CITY = "Please enter new Employee`s City of residence, use letters only:";
    private static final String PROVIDE_POSTAL_CODE = "Please enter new Employee`s postal code, use digits and only xx-xxx:";
    private static final String PROVIDE_STREET = "Please enter new Employee`s street, use letters and digits only";
    private static final String PROVIDE_STREET_NUMBER = "Please enter new Employee`s street number, use digits only";
    private static final String PROVIDE_APARTMENT_NUMBER = "Please enter new Employee`s apartment number, use digits only, press 0 to move forward:";

    public static final String CREATE_FINAL_NEGATIVE = "Sorry, we did not create new Employee, please try again";
    public static final String CREATE_FINAL_POSITIVE = "Employee was created, thank you.";

    public static final String FIND_EMPLOYEE_ID = "Please enter Employee`s ID you are looking for";
    private static final String FIND_FIRST_NAME = "Please enter Employee`s First Name you are looking for";
    private static final String FIND_LAST_NAME = "Please enter Employee`s Last Name  you are looking for";
    private static final String FIND_PERSONAL_ID_NUMBER = "Please enter Employee`s Personal ID Number you are looking for";
    private static final String FIND_CITY = "Please enter Employee`s City of residence you are looking for";
    private static final String FIND_POSTAL_CODE = "Please enter Employee`s postal code you are looking for";
    private static final String FIND_STREET = "Please enter Employee`s street you are looking for";
    private static final String FIND_STREET_NUMBER = "Please enter Employee`s street number you are looking for";
    private static final String FIND_APARTMENT_NUMBER = "Please enter Employee`s apartment number you are looking for";
    private static final String FIND_REMOVE_DATE = "Please enter Employee`s deactivation date you are looking for";

    public static final String FIND_FINAL = "Here are the chosen Employees:";
    public static final String FIND_FINAL_NEGATIVE = "Sorry, we did not find any correct match, please try again";

    public static final String DEACTIVATE_INITIAL_MESSAGE = "Enter ID of Employee you wish to deactivate";
    public static final String DEACTIVATE_FINAL_MESSAGE = "Employee is no longer active, but remains in the data base if needed";
    public static final String DEACTIVATE_NOT_ACTIVE_MESSAGE = "Employee is already not active.";

    public static final String CORRECT_CHECK_REMOVE_DATE = "Employee with this id is not working with the company anymore, please enter valid id.";

    public static void welcomeLoggedInEmployee(Employee employee) {
        UserIO.printMessage("Hi! " + employee.getFirstName());
    }

    public static void displayEmployee(Employee employee) {
        UserIO.printMessage(
                employee.getId() + " "
                        + employee.getFirstName() + " "
                        + employee.getLastName() + " "
                        + employee.getPersonalIdNumber() + " "
                        + employee.getCity() + " "
                        + employee.getPostalCode() + " "
                        + employee.getStreetName() + " "
                        + employee.getStreetNumber() + " "
                        + employee.getApartmentNumber() + " "
                        + employee.getRemoveDateTime()
        );
    }

    public static Long getCorrectCheckIdFind() {
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

    public static String getCorrectCheckFirstNameFind() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(EmployeeIO.FIND_FIRST_NAME);
                text = UserIO.getEnteredText();
                Validator.validateText(text);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_FIRST_NAME);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static String getCorrectCheckLastNameFind() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(EmployeeIO.FIND_LAST_NAME);
                text = UserIO.getEnteredText();
                Validator.validateText(text);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_LAST_NAME);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static String getCorrectCheckPersonalIdNumberFind() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(EmployeeIO.FIND_PERSONAL_ID_NUMBER);
                text = UserIO.getEnteredText();
                Validator.validateText(text);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_PERSONAL_ID_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static String getCorrectCheckCityFind() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(EmployeeIO.FIND_CITY);
                text = UserIO.getEnteredText();
                Validator.validateText(text);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_CITY);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static String getCorrectCheckPostalCodeFind() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(EmployeeIO.FIND_POSTAL_CODE);
                text = UserIO.getEnteredText();
                Validator.validatePostalCodeNumber(text);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_POSTAL_CODE);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static String getCorrectCheckStreetFind() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(EmployeeIO.FIND_STREET);
                text = UserIO.getEnteredText();
                Validator.validateText(text);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_STREET);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static Integer getCorrectCheckStreetNumberFind() {
        boolean shouldContinue;
        int number = 0;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(EmployeeIO.FIND_STREET_NUMBER);
                number = UserIO.getEnteredNumber();
                Validator.validateNumber(number);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_STREET_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return number;
    }

    public static Integer getCorrectCheckApartmentNumberFind() {
        boolean shouldContinue;
        int number = 0;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(EmployeeIO.FIND_APARTMENT_NUMBER);
                number = UserIO.getEnteredNumber();
                Validator.validateNumber(number);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_APARTMENT_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return number;
    }

    public static String getCorrectCheckFirstName() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_FIRST_NAME);
                text = UserIO.getEnteredText();
                Validator.validateText(text);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_FIRST_NAME);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static String getCorrectCheckLastName() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_LAST_NAME);
                text = UserIO.getEnteredText();
                Validator.validateText(text);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_LAST_NAME);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static String getCorrectCheckPersonalIdNumber() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_PERSONAL_ID_NUMBER);
                text = UserIO.getEnteredText();
                Validator.validatePersonalId(text);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_PERSONAL_ID_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static String getCorrectCheckCity() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_CITY);
                text = UserIO.getEnteredText();
                Validator.validateText(text);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_CITY);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static String getCorrectCheckPostalCode() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_POSTAL_CODE);
                text = UserIO.getEnteredText();
                Validator.validatePostalCodeNumber(text);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_POSTAL_CODE);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static String getCorrectCheckStreet() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_STREET);
                text = UserIO.getEnteredText();
                Validator.validateText(text);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_STREET);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static Integer getCorrectCheckStreetNumber() {
        boolean shouldContinue;
        int number = 0;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_STREET_NUMBER);
                number = UserIO.getEnteredNumber();
                Validator.validateNumber(number);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_STREET_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return number;
    }

    public static Integer getCorrectCheckApartmentNumber() {
        boolean shouldContinue;
        int number = 0;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_APARTMENT_NUMBER);
                number = UserIO.getEnteredNumber();
                Validator.validateNumber(number);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_APARTMENT_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return number;
    }

    public static String getCorrectCheckUpdateFirstName() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(EmployeeIO.CHANGE_FIRST_NAME);
                text = UserIO.getEnteredText();
                Validator.validateText(text);
            } catch (ValidationException e) {
                UserIO.printMessage(EmployeeIO.CORRECT_CHECK_FIRST_NAME);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static String getCorrectCheckUpdateLastName() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(EmployeeIO.CHANGE_LAST_NAME);
                text = UserIO.getEnteredText();
                Validator.validateText(text);
            } catch (ValidationException e) {
                UserIO.printMessage(EmployeeIO.CORRECT_CHECK_LAST_NAME);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static String getCorrectCheckUpdatePersonalIdNumber() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(EmployeeIO.CHANGE_PERSONAL_ID_NUMBER);
                text = UserIO.getEnteredText();
                Validator.validateText(text);
            } catch (ValidationException e) {
                UserIO.printMessage(EmployeeIO.CORRECT_CHECK_PERSONAL_ID_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static String getCorrectCheckUpdateCity() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(EmployeeIO.CHANGE_CITY);
                text = UserIO.getEnteredText();
                Validator.validateText(text);
            } catch (ValidationException e) {
                UserIO.printMessage(EmployeeIO.CORRECT_CHECK_LAST_NAME);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static String getCorrectCheckUpdatePostalCode() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(EmployeeIO.CHANGE_POSTAL_CODE);
                text = UserIO.getEnteredText();
                Validator.validateText(text);
            } catch (ValidationException e) {
                UserIO.printMessage(EmployeeIO.CORRECT_CHECK_POSTAL_CODE);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static String getCorrectCheckUpdateStreet() {
        boolean shouldContinue;
        String text = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(EmployeeIO.CHANGE_STREET);
                text = UserIO.getEnteredText();
                Validator.validateText(text);
            } catch (ValidationException e) {
                UserIO.printMessage(EmployeeIO.CORRECT_CHECK_STREET);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return text;
    }

    public static Integer getCorrectCheckUpdateStreetNumber() {
        boolean shouldContinue;
        Integer number = null;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(EmployeeIO.CHANGE_STREET_NUMBER);
                number = UserIO.getEnteredNumber();
                Validator.validateNumber(number);
            } catch (ValidationException e) {
                UserIO.printMessage(EmployeeIO.CORRECT_CHECK_STREET_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return number;
    }


    public static Integer getCorrectCheckUpdateApartmentNumber() {
        boolean shouldContinue;
        Integer number = null;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(EmployeeIO.CHANGE_APARTMENT_NUMBER);
                number = UserIO.getEnteredNumber();
                Validator.validateNumber(number);
            } catch (ValidationException e) {
                UserIO.printMessage(EmployeeIO.CORRECT_CHECK_APARTMENT_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return number;
    }


    // --------------------------------------
    //validation DataBaseExist | RemoveDate | Digits valid

    public static Long getCorrectCheckIdEmployee(EmployeeService employeeService) {
        boolean shouldContinue;
        Long number = null;
        do {
            shouldContinue = false;
            try {
                number = UserIO.getEnteredId();
                Validator.validateIdNumber(number);
                checkIfExistsInDBEmployee(employeeService, number);
                checkEmployeeRemoveDate(employeeService, number);
            } catch (ValidationException | EmployeeNotFoundException | NumberFormatException e) {
                UserIO.printMessage(EmployeeIO.CORRECT_CHECK_ID);
                shouldContinue = true;
            } catch (RemoveDateException e) {
                UserIO.printMessage(EmployeeIO.CORRECT_CHECK_REMOVE_DATE);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return number;
    }

    private static void checkIfExistsInDBEmployee(EmployeeService employeeService, Long id) {
        if (employeeService.get(id) == null) throw new EmployeeNotFoundException();
    }

    private static void checkEmployeeRemoveDate(EmployeeService employeeService, Long id) {
        Employee employee = employeeService.get(id);
        if (employee.getRemoveDateTime() != null) throw new RemoveDateException();
    }

    public static Long getCorrectCheckIdEmployeeUpdate(EmployeeService employeeService) {
        boolean shouldContinue;
        Long number = null;
        do {
            shouldContinue = false;
            try {
                number = UserIO.getEnteredId();
                Validator.validateIdNumber(number);
                checkIfExistsInDBEmployee(employeeService, number);
            } catch (ValidationException | EmployeeNotFoundException | NumberFormatException e) {
                UserIO.printMessage(EmployeeIO.CORRECT_CHECK_ID);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return number;
    }


}
