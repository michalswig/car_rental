package com.company.io;

import com.company.dto.Customer;
import com.company.exception.CustomerNotFoundException;
import com.company.exception.RemoveDateException;
import com.company.exception.ValidationException;
import com.company.service.CustomerService;
import com.company.validator.Validator;

public class CustomerIO {

    public static final String UPDATE_MAIN_MESSAGE = "What field do you wish to change?\n" +
            "    1. FirstName\n" +
            "    2. LastName\n" +
            "    3. PersonalIdNumber\n" +
            "    4. City\n" +
            "    5. PostalCode\n" +
            "    6. Street Name\n" +
            "    7. Street Number\n" +
            "    8. Street Apartment Number\n" +
            "    9. Company Name\n" +
            "    10. Tax ID Number\n" +
            "    11. Company City\n" +
            "    12. Company Postal Code\n" +
            "    13. Company Street Name\n" +
            "    14. Company Street Number\n" +
            "    15. Company Apartment Number";

    public static final String FIND_MAIN_MESSAGE = "   \n" +
            "   Find Customer in data base\n" +
            "   Choose the field you will use to find Customer by pressing number below and enter phrase You are looking for:\n" +
            "   1. by ID\n" +
            "   2. by First Name\n" +
            "   3. by Last Name\n" +
            "   4. by Personal ID number\n" +
            "   5. by City\n" +
            "   6. by Postal Code\n" +
            "   7. by Street Name\n" +
            "   8. by Street Number\n" +
            "   9. by Apartment Number\n" +
            "   10. by Company Name\n" +
            "   11. by Tax ID number\n" +
            "   12. by Company City\n" +
            "   13. by  Company Postal Code\n" +
            "   14. by  Company Street\n" +
            "   15. by  Company Street Number\n" +
            "   16. by  Company Apartment Number\n" +
            "   17. by  Deactivation Date dd/mm/yyy";


    public static final String CREATE_INITIAL_MESSAGE = "Start to enter data of a new Customer:";
    public static final String VEHICLE_RENTAL_MESSAGE = "Choose customerID you want to rent the vehicle:";
    public static final String GET_INITIAL_MESSAGE = "Enter ID of Customer you wish to display";
    public static final String DEACTIVATE_INITIAL_MESSAGE = "Enter ID of Customer you wish to deactivate";
    public static final String DEACTIVATE_NOT_ACTIVE_MESSAGE = "Customer is already not active.";
    public static final String UPDATE_INITIAL_MESSAGE = "Enter ID of Customer you wish to update:";
    public static final String SHOW_ALL_CUSTOMERS_MESSAGE = "Here is the list of all customers:";
    public static final String UPDATE_FINAL_MESSAGE = "Customer is properly updated in the data base";
    public static final String GET_FINAL_MESSAGE = "Thank you.";
    public static final String DEACTIVATE_FINAL_MESSAGE = "Customer is no longer active, but remains in the data base if needed";
    public static final String FINAL_CREATE_MESSAGE = "Customer is properly loaded into the system";
    private static final String CORRECT_CHECK_FIRST_NAME = "First name is incorrect";
    private static final String CORRECT_CHECK_LAST_NAME = "Last name is incorrect";
    private static final String CORRECT_CHECK_PERSONAL_ID_NUMBER = "Personal ID number is incorrect";
    private static final String CORRECT_CHECK_CITY = "City is incorrect";
    private static final String CORRECT_CHECK_POSTAL_CODE = "Postal Code number is incorrect.";
    private static final String CORRECT_CHECK_STREET = "Street is incorrect";
    private static final String CORRECT_CHECK_STREET_NUMBER = "Street number is incorrect";
    private static final String CORRECT_CHECK_APARTMENT_NUMBER = "Apartment number is incorrect";
    private static final String CORRECT_CHECK_COMPANY_NAME = "Company name is incorrect";
    private static final String CORRECT_CHECK_TAX_ID_NUMBER = "NIP number is incorrect";
    private static final String CORRECT_CHECK_COMPANY_CITY = "Company city is incorrect";
    private static final String CORRECT_CHECK_COMPANY_POSTAL_CODE = "Company postal code is incorrect";
    private static final String CORRECT_CHECK_COMPANY_STREET = "Company street is incorrect";
    private static final String CORRECT_CHECK_COMPANY_STREET_NUMBER = "Company street number is incorrect";
    private static final String CORRECT_CHECK_COMPANY_APARTMENT_NUMBER = "Company apartment number is incorrect";
    private static final String PROVIDE_FIRST_NAME = "Please enter new Customer`s First Name, use letters only:";
    private static final String PROVIDE_LAST_NAME = "Please enter new Customer`s Last Name, use letters only:";
    private static final String PROVIDE_PERSONAL_ID_NUMBER = "Please enter new Customer`s Personal ID Number, use digits only (max 25 digits):";
    private static final String PROVIDE_CITY = "Please enter new Customer`s City of residence, use letters only:";
    private static final String PROVIDE_POSTAL_CODE = "Please enter new Customer`s postal code, use digits and only xx-xxx:";
    private static final String PROVIDE_STREET = "Please enter new Customer`s street, use letters and digits only";
    private static final String PROVIDE_STREET_NUMBER = "Please enter new Customer`s street number, use digits only";
    private static final String PROVIDE_APARTMENT_NUMBER = "Please enter new Customer`s apartment number, use digits only, press 0 to move forward:";
    private static final String PROVIDE_COMPANY_NAME = "If applicable, please enter new Customer`s Company Name, else, press 0 to move forward:";
    private static final String PROVIDE_NIP_NUMBER = "If applicable, please enter new Customer`s Tax ID number, else, press 0 to move forward, accepted: XXX-XX-XX-XXX ; XXX-XXX-XX-XX ; XXX XX XX XXX ; XXX XXX XX XX:";
    private static final String PROVIDE_COMPANY_CITY = "If applicable, please enter new Customer`s Company City, else, press 0 to move forward:";
    private static final String PROVIDE_COMPANY_POSTAL_CODE = "If applicable, please enter new Customer`s Company Postal Code, else, press 0 to move forward:";
    private static final String PROVIDE_COMPANY_STREET = "If applicable, please enter new Customer`s Company street, else, press 0 to move forward:";
    private static final String PROVIDE_COMPANY_STREET_NUMBER = "If applicable, please enter new Customer`s Company street number, use digits only. else, press 0 to move forward:";
    private static final String PROVIDE_COMPANY_APARTMENT_NUMBER = "If applicable, please enter new Customer`s Company apartment number, use digits only, else, press 0 to move forward:";

    public static final String FIND_CUSTOMER_ID = "Please enter Customer`s ID you are looking for";
    private static final String FIND_FIRST_NAME = "Please enter Customer`s First Name you are looking for";
    private static final String FIND_LAST_NAME = "Please enter Customer`s Last Name  you are looking for";
    private static final String FIND_PERSONAL_ID_NUMBER = "Please enter Customer`s Personal ID Number you are looking for";
    private static final String FIND_CITY = "Please enter Customer`s City of residence you are looking for";
    private static final String FIND_POSTAL_CODE = "Please enter Customer`s postal code you are looking for";
    private static final String FIND_STREET = "Please enter Customer`s street you are looking for";
    private static final String FIND_STREET_NUMBER = "Please enter Customer`s street number you are looking for";
    private static final String FIND_APARTMENT_NUMBER = "Please enter Customer`s apartment number you are looking for";
    private static final String FIND_COMPANY_NAME = "Please enter Customer`s Company Name you are looking for";
    private static final String FIND_TAX_ID_NUMBER = "Please enter Customer`s Tax ID number you are looking for";
    private static final String FIND_COMPANY_CITY = "Please enter Customer`s Company City you are looking for";
    private static final String FIND_COMPANY_POSTAL_CODE = "Please enter Customer`s Company Postal Code you are looking for";
    private static final String FIND_COMPANY_STREET = "Please enter Customer`s Company street you are looking for";
    private static final String FIND_COMPANY_STREET_NUMBER = "Please enter Customer`s Company street number you are looking for";
    private static final String FIND_COMPANY_APARTMENT_NUMBER = "Please enter Customer`s Company apartment number you are looking for";
    private static final String FIND_REMOVE_DATE = "Please enter Customer`s RemoveDate, you are looking for";
    public static final String FIND_FINAL = "Here are the chosen Customers:";
    public static final String FIND_FINAL_NEGATIVE = "Sorry, we did not find any correct match, please try again";

    private static final String CHANGE_FIRST_NAME = "Please enter new Customer`s First Name, use letters only:";
    private static final String CHANGE_LAST_NAME = "Please enter new Customer`s Last Name, use letters only:";
    private static final String CHANGE_PERSONAL_ID_NUMBER = "Please enter new Customer`s Personal ID Number, use digits only:";
    private static final String CHANGE_CITY = "Please enter new Customer`s City of residence, use letters only:";
    private static final String CHANGE_POSTAL_CODE = "Please enter new Customer`s postal code, use digits and only xx-xxx:";
    private static final String CHANGE_STREET = "Please enter new Customer`s street, use letters and digits only";
    private static final String CHANGE_STREET_NUMBER = "Please enter new Customer`s street number, use digits only";
    private static final String CHANGE_APARTMENT_NUMBER = "Please enter new Customer`s apartment number, use digits only";
    private static final String CHANGE_COMPANY_NAME = "Please enter new Customer`s Company Name use letters only";
    private static final String CHANGE_TAX_ID_NUMBER = "If applicable, please enter new Customer`s Tax ID number, use letters only";
    private static final String CHANGE_COMPANY_CITY = "If applicable, please enter new Customer`s Company City, use letters only";
    private static final String CHANGE_COMPANY_POSTAL_CODE = "If applicable, please enter new Customer`s Company Postal Code, use letters only";
    private static final String CHANGE_COMPANY_STREET = "If applicable, please enter new Customer`s Company street, use letters only";
    private static final String CHANGE_COMPANY_STREET_NUMBER = "If applicable, please enter new Customer`s Company street number, use digits only";
    private static final String CHANGE_COMPANY_APARTMENT_NUMBER = "If applicable, please enter new Customer`s Company apartment number, use digits only";

    public static final String PROVIDE_ID = "Please enter new Customer`s ID, use digits only:";
    public static final String CORRECT_CHECK_ID = "ID is incorrect, please enter correct ID";

    public static final String CORRECT_CHECK_REMOVE_DATE = "Customer is not active, please enter ID of active Customer or enter new Customer to the data base";

    public static final String CORRECT_CHECK_UPDATE_MENU = "Please enter valid menu number to move forward";

    public static Long getIdToFind() {

        boolean shouldContinue;
        Long number = null;
        do {
            UserIO.printMessage(CustomerIO.FIND_CUSTOMER_ID);
            shouldContinue = false;
            try {
                number = UserIO.getEnteredId();
                Validator.validateIdNumber(number);
            } catch (ValidationException | CustomerNotFoundException | NumberFormatException e) {
                UserIO.printMessage(CustomerIO.CORRECT_CHECK_ID);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return number;
    }

    public static String getFirstNameToFind() {
        boolean shouldContinue;
        String firstName = "";
        do {
            UserIO.printMessage(CustomerIO.FIND_FIRST_NAME);
            shouldContinue = false;
            try {
                firstName = UserIO.getEnteredText();
                Validator.validateText(firstName);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_FIRST_NAME);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return firstName;
    }

    public static String getLastNameToFind() {
        boolean shouldContinue;
        String lastName = "";
        do {
            UserIO.printMessage(CustomerIO.FIND_LAST_NAME);
            shouldContinue = false;
            try {
                lastName = UserIO.getEnteredText();
                Validator.validateText(lastName);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_LAST_NAME);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return lastName;
    }

    public static String getPersonalIdNumberToFind() {
        boolean shouldContinue;
        String personalIdNumber = "";
        do {
            UserIO.printMessage(CustomerIO.FIND_PERSONAL_ID_NUMBER);
            shouldContinue = false;
            try {
                personalIdNumber = UserIO.getEnteredText();
                Validator.validatePersonalId(personalIdNumber);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_PERSONAL_ID_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return personalIdNumber;
    }

    public static String getCityToFind() {
        boolean shouldContinue;
        String city = "";
        do {
            UserIO.printMessage(CustomerIO.FIND_CITY);
            shouldContinue = false;
            try {
                city = UserIO.getEnteredText();
                Validator.validateText(city);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_CITY);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return city;
    }

    public static String getPostalCodeToFind() {
        boolean shouldContinue;
        String postalCode = "";
        do {
            UserIO.printMessage(CustomerIO.FIND_POSTAL_CODE);
            shouldContinue = false;
            try {
                postalCode = UserIO.getEnteredText();
                Validator.validatePostalCodeNumber(postalCode);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_POSTAL_CODE);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return postalCode;
    }

    public static String getStreetToFind() {
        boolean shouldContinue;
        String street = "";
        do {
            UserIO.printMessage(CustomerIO.FIND_STREET);
            shouldContinue = false;
            try {
                street = UserIO.getEnteredText();
                Validator.validateText(street);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_STREET);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return street;
    }

    public static Integer getStreetNumberToFind() {
        boolean shouldContinue;
        int streetNumber = 0;
        do {
            UserIO.printMessage(CustomerIO.FIND_STREET_NUMBER);
            shouldContinue = false;
            try {
                streetNumber = UserIO.getEnteredNumber();
                Validator.validateNumber(streetNumber);
            } catch (ValidationException | NumberFormatException e) {
                UserIO.printMessage(CORRECT_CHECK_STREET_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return streetNumber;
    }

    public static Integer getApartmentNumberToFind() {
        boolean shouldContinue;
        Integer number = 0;
        do {
            UserIO.printMessage(CustomerIO.FIND_APARTMENT_NUMBER);
            shouldContinue = false;
            try {
                number = UserIO.getEnteredNumber();
                Validator.validateNumber(number);
            } catch (ValidationException | NumberFormatException e) {
                UserIO.printMessage(CORRECT_CHECK_APARTMENT_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return number;
    }

    public static String getCompanyNameToFind() {
        boolean shouldContinue;
        String companyName = "";
        do {
            UserIO.printMessage(CustomerIO.FIND_COMPANY_NAME);
            shouldContinue = false;
            try {
                companyName = UserIO.getEnteredText();
                Validator.validateText(companyName);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_COMPANY_NAME);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return companyName;
    }

    public static String getNIPNumberToFind() {
        boolean shouldContinue;
        String taxIdNumber = "";
        do {
            UserIO.printMessage(CustomerIO.FIND_TAX_ID_NUMBER);
            shouldContinue = false;
            try {
                taxIdNumber = UserIO.getEnteredText();
                Validator.validateNIPNumber(taxIdNumber);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_TAX_ID_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return taxIdNumber;
    }

    public static String getCompanyCityToFind() {
        boolean shouldContinue;
        String companyCity = "";
        do {
            UserIO.printMessage(CustomerIO.FIND_COMPANY_CITY);
            shouldContinue = false;
            try {
                companyCity = UserIO.getEnteredText();
                Validator.validateText(companyCity);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_COMPANY_CITY);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return companyCity;
    }

    public static String getCompanyPostalCodeToFind() {
        boolean shouldContinue;
        String companyPostalCode = "";
        do {
            UserIO.printMessage(CustomerIO.FIND_COMPANY_POSTAL_CODE);
            shouldContinue = false;
            try {
                companyPostalCode = UserIO.getEnteredText();
                Validator.validatePostalCodeNumber(companyPostalCode);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_COMPANY_POSTAL_CODE);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return companyPostalCode;
    }

    public static String getCompanyStreetToFind() {
        boolean shouldContinue;
        String companyStreet = "";
        do {
            UserIO.printMessage(CustomerIO.FIND_COMPANY_STREET);
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_COMPANY_STREET);
                companyStreet = UserIO.getEnteredText();
                Validator.validateText(companyStreet);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_COMPANY_STREET);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return companyStreet;
    }

    public static Integer getCompanyStreetNumberToFind() {
        boolean shouldContinue;
        Integer companyStreetNumber = 0;
        do {
            UserIO.printMessage(CustomerIO.FIND_COMPANY_STREET_NUMBER);
            shouldContinue = false;
            try {
                companyStreetNumber = UserIO.getEnteredNumber();
                Validator.validateNumber(companyStreetNumber);
            } catch (ValidationException | NumberFormatException e) {
                UserIO.printMessage(CORRECT_CHECK_COMPANY_STREET_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return companyStreetNumber;
    }

    public static Integer getCompanyApartmentNumberToFind() {
        boolean shouldContinue;
        Integer companyApartmentNumber = 0;
        do {
            UserIO.printMessage(CustomerIO.FIND_COMPANY_APARTMENT_NUMBER);
            shouldContinue = false;
            try {
                companyApartmentNumber = UserIO.getEnteredNumber();
                Validator.validateNumber(companyApartmentNumber);
            } catch (ValidationException | NumberFormatException e) {
                UserIO.printMessage(CORRECT_CHECK_COMPANY_APARTMENT_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return companyApartmentNumber;
    }

    public static Long getCorrectCheckId() {
        boolean shouldContinue;
        Long number = null;
        do {
            shouldContinue = false;
            try {
                number = UserIO.getEnteredId();
                Validator.validateIdNumber(number);
            } catch (ValidationException | CustomerNotFoundException | NumberFormatException e) {
                UserIO.printMessage(CustomerIO.CORRECT_CHECK_ID);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return number;
    }

    public static String getCorrectCheckFirstName() {
        boolean shouldContinue;
        String firstName = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_FIRST_NAME);
                firstName = UserIO.getEnteredText();
                Validator.validateText(firstName);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_FIRST_NAME);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return firstName;
    }

    public static String getCorrectCheckLastName() {
        boolean shouldContinue;
        String lastName = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_LAST_NAME);
                lastName = UserIO.getEnteredText();
                Validator.validateText(lastName);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_LAST_NAME);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return lastName;
    }

    public static String getCorrectCheckPersonalIdNumber() {
        boolean shouldContinue;
        String personalIdNumber = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_PERSONAL_ID_NUMBER);
                personalIdNumber = UserIO.getEnteredText();
                Validator.validatePersonalId(personalIdNumber);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_PERSONAL_ID_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return personalIdNumber;
    }

    public static String getCorrectCheckCity() {
        boolean shouldContinue;
        String city = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_CITY);
                city = UserIO.getEnteredText();
                Validator.validateText(city);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_CITY);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return city;
    }

    public static String getCorrectCheckPostalCode() {
        boolean shouldContinue;
        String postalCode = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_POSTAL_CODE);
                postalCode = UserIO.getEnteredText();
                Validator.validatePostalCodeNumber(postalCode);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_POSTAL_CODE);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return postalCode;
    }

    public static String getCorrectCheckStreet() {
        boolean shouldContinue;
        String street = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_STREET);
                street = UserIO.getEnteredText();
                Validator.validateText(street);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_STREET);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return street;
    }

    public static Integer getCorrectCheckStreetNumber() {
        boolean shouldContinue;
        int streetNumber = 0;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_STREET_NUMBER);
                streetNumber = UserIO.getEnteredNumber();
                Validator.validateNumber(streetNumber);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_STREET_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return streetNumber;
    }

    public static Integer getCorrectCheckApartmentNumber() {
        boolean shouldContinue;
        Integer number = 0;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_APARTMENT_NUMBER);
                number = UserIO.getEnteredNumber();
                if (number == 0) number = null;
                else Validator.validateNumber(number);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_APARTMENT_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return number;
    }

    public static String getCorrectCheckCompanyName() {
        boolean shouldContinue;
        String companyName = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_COMPANY_NAME);
                companyName = UserIO.getEnteredText();
                if (companyName.equals("0")) companyName = null;
                else Validator.validateText(companyName);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_COMPANY_NAME);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return companyName;
    }

    public static String getCorrectCheckNIPNumber() {
        boolean shouldContinue;
        String taxIdNumber = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_NIP_NUMBER);
                taxIdNumber = UserIO.getEnteredText();
                if (taxIdNumber.equals("0")) taxIdNumber = null;
                else Validator.validateNIPNumber(taxIdNumber);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_TAX_ID_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return taxIdNumber;
    }

    public static String getCorrectCheckCompanyCity() {
        boolean shouldContinue;
        String companyCity = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_COMPANY_CITY);
                companyCity = UserIO.getEnteredText();
                if (companyCity.equals("0")) companyCity = null;
                else Validator.validateText(companyCity);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_COMPANY_CITY);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return companyCity;
    }

    public static String getCorrectCheckCompanyPostalCode() {
        boolean shouldContinue;
        String companyPostalCode = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_COMPANY_POSTAL_CODE);
                companyPostalCode = UserIO.getEnteredText();
                if (companyPostalCode.equals("0")) companyPostalCode = null;
                else Validator.validatePostalCodeNumber(companyPostalCode);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_COMPANY_POSTAL_CODE);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return companyPostalCode;
    }

    public static Integer getCorrectCheckCompanyStreetNumber() {
        boolean shouldContinue;
        Integer companyStreetNumber = 0;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_COMPANY_STREET_NUMBER);
                companyStreetNumber = UserIO.getEnteredNumber();
                if (companyStreetNumber == 0) companyStreetNumber = null;
                else Validator.validateNumber(companyStreetNumber);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_COMPANY_STREET_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return companyStreetNumber;
    }

    public static Integer getCorrectCheckCompanyApartmentNumber() {
        boolean shouldContinue;
        Integer companyApartmentNumber = 0;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_COMPANY_APARTMENT_NUMBER);
                companyApartmentNumber = UserIO.getEnteredNumber();
                if (companyApartmentNumber == 0) companyApartmentNumber = null;
                else Validator.validateNumber(companyApartmentNumber);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_COMPANY_APARTMENT_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return companyApartmentNumber;
    }

    public static String getCorrectCheckCompanyStreet() {
        boolean shouldContinue;
        String companyStreet = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(PROVIDE_COMPANY_STREET);
                companyStreet = UserIO.getEnteredText();
                if (companyStreet.equals("0")) companyStreet = null;
                else Validator.validateText(companyStreet);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_COMPANY_STREET);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return companyStreet;
    }

    public static void displayCustomer(Customer customer) {
        UserIO.printMessage(
                customer.getId() + " "
                        + customer.getFirstName() + " "
                        + customer.getLastName() + " "
                        + customer.getPersonalIdNumber() + " "
                        + customer.getCity() + " "
                        + customer.getPostalCode() + " "
                        + customer.getStreet() + " "
                        + customer.getStreetNumber() + " "
                        + customer.getApartmentNumber() + " "
                        + customer.getCompanyName() + " "
                        + customer.getTaxIdNumber() + " "
                        + customer.getCompanyPostalCode() + " "
                        + customer.getCompanyStreet() + " "
                        + customer.getCompanyStreetNumber() + " "
                        + customer.getCompanyApartmentNumber() + " "
                        + customer.getRemoveDateTime() + " "
        );
    }

    public static String getCorrectCheckUpdateFirstName() {
        boolean shouldContinue;
        String firstName = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(CustomerIO.CHANGE_FIRST_NAME);
                firstName = UserIO.getEnteredText();
                Validator.validateText(firstName);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_FIRST_NAME);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return firstName;
    }

    public static String getCorrectCheckUpdateLastName() {
        boolean shouldContinue;
        String lastName = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(CHANGE_LAST_NAME);
                lastName = UserIO.getEnteredText();
                Validator.validateText(lastName);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_LAST_NAME);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return lastName;
    }

    public static String getCorrectCheckUpdatePersonalIdNumber() {
        boolean shouldContinue;
        String personalIdNumber = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(CHANGE_PERSONAL_ID_NUMBER);
                personalIdNumber = UserIO.getEnteredText();
                Validator.validatePersonalId(personalIdNumber);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_PERSONAL_ID_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return personalIdNumber;
    }

    public static String getCorrectCheckUpdateCity() {
        boolean shouldContinue;
        String city = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(CHANGE_CITY);
                city = UserIO.getEnteredText();
                Validator.validateText(city);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_LAST_NAME);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return city;
    }

    public static String getCorrectCheckUpdatePostalCode() {
        boolean shouldContinue;
        String postalCode = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(CHANGE_POSTAL_CODE);
                postalCode = UserIO.getEnteredText();
                Validator.validateText(postalCode);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_POSTAL_CODE);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return postalCode;
    }

    public static String getCorrectCheckUpdateStreet() {
        boolean shouldContinue;
        String street = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(CHANGE_STREET);
                street = UserIO.getEnteredText();
                Validator.validateText(street);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_STREET);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return street;
    }

    public static Integer getCorrectCheckUpdateStreetNumber() {
        boolean shouldContinue;
        String streetNumber = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(CHANGE_STREET_NUMBER);
                streetNumber = UserIO.getEnteredText();
                Validator.validateText(streetNumber);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_STREET_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return Integer.parseInt(streetNumber);
    }

    public static Integer getCorrectCheckUpdateApartmentNumber() {
        boolean shouldContinue;
        String apartmentNumber = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(CHANGE_APARTMENT_NUMBER);
                apartmentNumber = UserIO.getEnteredText();
                Validator.validateText(apartmentNumber);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_APARTMENT_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return Integer.parseInt(apartmentNumber);
    }

    public static String getCorrectCheckUpdateCompanyName() {
        boolean shouldContinue;
        String companyName = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(CHANGE_COMPANY_NAME);
                companyName = UserIO.getEnteredText();
                Validator.validateText(companyName);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_COMPANY_NAME);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return companyName;
    }

    public static String getCorrectCheckUpdateTaxIdNumber() {
        boolean shouldContinue;
        String taxIdNumber = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(CHANGE_TAX_ID_NUMBER);
                taxIdNumber = UserIO.getEnteredText();
                Validator.validateText(taxIdNumber);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_TAX_ID_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return taxIdNumber;
    }

    public static String getCorrectCheckUpdateCompanyCity() {
        boolean shouldContinue;
        String companyCity = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(CHANGE_COMPANY_CITY);
                companyCity = UserIO.getEnteredText();
                Validator.validateText(companyCity);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_COMPANY_CITY);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return companyCity;
    }

    public static String getCorrectCheckUpdateCompanyPostalCode() {
        boolean shouldContinue;
        String companyPostalCode = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(CHANGE_COMPANY_POSTAL_CODE);
                companyPostalCode = UserIO.getEnteredText();
                Validator.validateText(companyPostalCode);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_COMPANY_POSTAL_CODE);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return companyPostalCode;
    }

    public static String getCorrectCheckUpdateCompanyStreet() {
        boolean shouldContinue;
        String companyStreet = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(CHANGE_COMPANY_STREET);
                companyStreet = UserIO.getEnteredText();
                Validator.validateText(companyStreet);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_COMPANY_STREET);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return companyStreet;
    }

    public static Integer getCorrectCheckUpdateCompanyStreetNumber() {
        boolean shouldContinue;
        String companyStreet = "";
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(CHANGE_COMPANY_STREET_NUMBER);
                companyStreet = UserIO.getEnteredText();
                Validator.validateText(companyStreet);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_COMPANY_STREET_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return Integer.parseInt(companyStreet);
    }

    public static Integer getCorrectCheckUpdateCompanyApartmentNumber() {
        boolean shouldContinue;
        Integer companyStreet = null;
        do {
            shouldContinue = false;
            try {
                UserIO.printMessage(CHANGE_COMPANY_APARTMENT_NUMBER);
                companyStreet = UserIO.getEnteredNumber();
                Validator.validateNumber(companyStreet);
            } catch (ValidationException e) {
                UserIO.printMessage(CORRECT_CHECK_COMPANY_APARTMENT_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return companyStreet;
    }

//    ===============================================

    public static Long getCorrectCheckIdActiveCustomer(CustomerService customerService) {
        boolean shouldContinue;
        Long number = null;
        do {
            shouldContinue = false;
            try {
                number = UserIO.getEnteredId();
                Validator.validateIdNumber(number);
                checkIfExistsInDBCustomer(customerService, number);
                checkCustomerRemoveDate(customerService, number);
            } catch (ValidationException | CustomerNotFoundException | NumberFormatException e) {
                UserIO.printMessage(CustomerIO.CORRECT_CHECK_ID);
                shouldContinue = true;
            } catch (RemoveDateException e) {
                UserIO.printMessage(CustomerIO.CORRECT_CHECK_REMOVE_DATE);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return number;
    }


    //----UPDATE
    //validation DataBaseExist | RemoveDate | Digits valid

    public static Long getCorrectCheckIdCustomerUpdate(CustomerService customerService) {
        boolean shouldContinue;
        Long number = null;
        do {
            shouldContinue = false;
            try {
                number = UserIO.getEnteredId();
                Validator.validateIdNumber(number);
                checkIfExistsInDBCustomer(customerService, number);
            } catch (ValidationException | CustomerNotFoundException | NumberFormatException e) {
                UserIO.printMessage(CustomerIO.CORRECT_CHECK_ID);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return number;
    }

    //----

    private static void checkIfExistsInDBCustomer(CustomerService customerService, Long id) {
        if (customerService.get(id) == null) throw new CustomerNotFoundException();
    }

    private static void checkCustomerRemoveDate(CustomerService customerService, Long id) {
        Customer customer = customerService.get(id);
        if (customer.getRemoveDateTime() != null) throw new RemoveDateException();
    }


}
