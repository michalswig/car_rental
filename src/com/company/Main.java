package com.company;

import com.company.dao.*;
import com.company.dto.*;
import com.company.enumchoice.*;
import com.company.exception.*;
import com.company.io.*;
import com.company.service.*;
import com.company.table.*;
import com.company.validator.Validator;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private final static String URL = "jdbc:mysql://localhost:3306/car_rental";
    private final static String USERNAME = "learn_sql_user";
    private final static String PASSWORD = "mike";


    public static void main(String[] args) {

        boolean stopTrigger = true;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            CustomerDAO customerDAO = new CustomerDAO(connection);
            RentalDetailsDAO rentalDetailsDAO = new RentalDetailsDAO(connection);
            EmployeeDAO employeeDAO = new EmployeeDAO(connection);
            VehicleDAO vehicleDAO = new VehicleDAO(connection);
            CustomerService customerService = new CustomerService(customerDAO);
            RentalDetailsService rentalDetailsService = new RentalDetailsService(rentalDetailsDAO);
            EmployeeService employeeService = new EmployeeService(employeeDAO);
            VehicleService vehicleService = new VehicleService(vehicleDAO);
            CommonDAO commonDAO = new CommonDAO(connection);
            VehicleRateStatsDAO vehicleRateStatsDao = new VehicleRateStatsDAO(connection);
            VehicleRateStatsService vehicleRateStatsService = new VehicleRateStatsService(vehicleRateStatsDao);
            CommonService commonService = new CommonService(commonDAO);
            do {
                UserIO.printMessage(UserIO.WELCOME_MESSAGE_MAIN);
                switch (Menu.prepareMenuItem(getValidMenuChoice(Menu.values()[0].getItemNumber(), Menu.values().length))) {
                    case CUSTOMER_CREATE -> performCustomerCreation(customerService, commonDAO);
                    case CUSTOMER_UPDATE -> performCustomerUpdate(customerService);
                    case CUSTOMER_FIND -> performFindCustomer(customerService);
                    case CUSTOMER_DEACTIVATE -> performCustomerDeactivation(customerService);
                    case VEHICLE_CREATE -> performVehicleCreation(vehicleService, commonDAO);
                    case VEHICLE_UPDATE -> performVehicleUpdate(vehicleService, vehicleRateStatsService);
                    case VEHICLE_FIND -> performVehicleFind(vehicleService);
                    case VEHICLE_DEACTIVATE -> performVehicleDeactivation(vehicleService);
                    case EMPLOYEE_CREATE -> performEmployeeCreation(employeeService, commonDAO);
                    case EMPLOYEE_UPDATE -> performEmployeeUpdate(employeeService);
                    case EMPLOYEE_FIND -> performEmployeeFind(employeeService);
                    case EMPLOYEE_DEACTIVATE -> performEmployeeDeactivation(employeeService);
                    case RENTAL_CREATE -> performRentalCreation(customerService, rentalDetailsService, employeeService, vehicleService, commonDAO);
                    case RENTAL_FIND -> performRentalFind(rentalDetailsService);
                    case VEHICLE_RATE_STATS_FIND -> performVehicleRateStatsFind(vehicleRateStatsService);
                    case TOP_EARNERS_REPORT_MONTH -> performTopEarnersReportMonth(commonService);
                    case TOP_EARNERS_REPORT_YEAR -> performTopEarnersReportYear(commonService);
                    case TOP_EARNERS_REPORT_RENTED_DAYS -> performTopEarnersRentedDaysReport(commonService);
                    case APPLICATION_EXIT -> {
                        UserIO.printMessage(UserIO.FINAL_MESSAGE_MAIN);
                        stopTrigger = false;
                    }
                }
            } while (stopTrigger);
        } catch (DataBaseException | SQLException e) {
            UserIO.printMessage(UserIO.DATA_BASE_ERROR + e.getMessage());
        }
    }

    private static void performTopEarnersReportMonth(CommonService commonService) {
        UserIO.printMessage(UserIO.TOP_EARNERS_REPORT_MONTH);
        displayTable(prepareRowsTableTopEarnersByMonthReport(commonService.getTopEarnerMonth()), prepareHeaderTableTopEarnersByMonthReport());
    }

    private static void performTopEarnersReportYear(CommonService commonService) {
        UserIO.printMessage(UserIO.TOP_EARNERS_REPORT_YEAR);
        displayTable(prepareRowsTableTopEarnersByYearReport(commonService.getTopEarnerYear()), prepareHeaderTableTopEarnersByYearReport());
    }

    private static void performTopEarnersRentedDaysReport(CommonService commonService) {
        UserIO.printMessage(UserIO.TOP_EARNERS_REPORT_RENTED_DAYS);
        displayTable(prepareRowsTableTopEarnersByRentedDaysReport(commonService.getTopEarnerByRentedDays()), prepareHeaderTableTopEarnersByRentedDaysReport());
    }

    private static void performVehicleRateStatsFind(VehicleRateStatsService vehicleRateStatsService) {
        try {
            UserIO.printMessage(VehicleRateStatsIO.SHOW_ALL_CHANGES_MESSAGE);
            List<VehicleRateStats> vehicleRateStatsAll = vehicleRateStatsService.getAll();
            displayTable(prepareRowsTableVehicleRateChange(vehicleRateStatsAll), prepareHeaderTableVehicleRateChange());
            int firstItemValue = MenuFindVehicleRateStats.values()[0].getItemNumber();
            int lastItemValue = MenuFindVehicleRateStats.values().length;
            UserIO.printMessage(VehicleRateStatsIO.FIND_MAIN_MESSAGE);
            Integer enteredNumber = getValidMenuChoice(firstItemValue, lastItemValue);
            VehicleRateStats vehicleRateStat = prepareChosenVehicleRateStats(enteredNumber);
            List<VehicleRateStats> vehicleRateStats = vehicleRateStatsService.find(vehicleRateStat);
            if (vehicleRateStats.isEmpty()) {
                UserIO.printMessage(VehicleRateStatsIO.FIND_FINAL_NEGATIVE);
            }
            UserIO.printMessage(VehicleRateStatsIO.FIND_FINAL);
            displayTable(prepareRowsTableVehicleRateChange(vehicleRateStats), prepareHeaderTableVehicleRateChange());
        } catch (VehicleRateStatsNotFoundException e) {
            UserIO.printMessage(VehicleRateStatsIO.FIND_FINAL_NEGATIVE);
        }
    }

    private static void performRentalFind(RentalDetailsService rentalDetailsService) {
        displayTable(prepareRowsTableRentalDetails(rentalDetailsService.getAll()), prepareHeaderTableRentalDetails());
        int firstItemValue = MenuFindRentalDetails.values()[0].getMenuItem();
        int lastItemValue = MenuFindRentalDetails.values().length;
        UserIO.printMessage(RentalDetailsIO.FIND_MAIN_MESSAGE);
        Integer enteredNumber = getValidMenuChoice(firstItemValue, lastItemValue);
        RentalDetails rentalDetail = prepareChosenRentalDetails(enteredNumber);
        try {
            List<RentalDetails> rentalDetails = rentalDetailsService.find(rentalDetail);
            if (rentalDetails.isEmpty()) {
                UserIO.printMessage(UserIO.FIND_NEGATIVE);
            } else {
                UserIO.printMessage(RentalDetailsIO.FIND_FINAL_POSITIVE);
                displayTable(prepareRowsTableRentalDetails(rentalDetails), prepareHeaderTableRentalDetails());
            }
        } catch (RentalDetailsNotFoundException e) {
            UserIO.printMessage(RentalDetailsIO.FIND_FINAL_NEGATIVE);
        }
    }

    private static void performRentalCreation(CustomerService customerService, RentalDetailsService rentalDetailsService, EmployeeService employeeService, VehicleService vehicleService, CommonDAO commonDAO) {
        UserIO.printMessage(EmployeeIO.SHOW_ALL_EMPLOYEES_MESSAGE);
        displayTable(prepareRowsTableEmployee(employeeService.getALl()), prepareHeaderTableEmployee());
        UserIO.printMessage(EmployeeIO.GET_ID_MESSAGE);
        Long correctCheckIdEmployee = EmployeeIO.getCorrectCheckIdEmployee(employeeService);
        Employee employee = employeeService.get(correctCheckIdEmployee);
        UserIO.printMessage(CustomerIO.SHOW_ALL_CUSTOMERS_MESSAGE);
        displayTable(prepareRowsTableCustomer(customerService.getAll()), prepareHeaderTableCustomer());
        UserIO.printMessage(CustomerIO.VEHICLE_RENTAL_MESSAGE);
        Long correctCheckIdCustomer = CustomerIO.getCorrectCheckIdActiveCustomer(customerService);
        Customer customer = customerService.get(correctCheckIdCustomer);
        UserIO.printMessage(VehicleIO.SHOW_ALL_VEHICLES_MESSAGE);
        displayTable(prepareRowsTableVehicle(vehicleService.getAll()), prepareHeaderTableVehicle());
        UserIO.printMessage(VehicleIO.GET_ID_MESSAGE);
        Long correctCheckIdVehicle = VehicleIO.getCorrectCheckIdVehicle(vehicleService);
        Vehicle vehicle = vehicleService.get(correctCheckIdVehicle);
        UserIO.printMessage(VehicleIO.PICK_UP_DATE_MESSAGE);
        LocalDate pickUpDate = VehicleIO.getCorrectCheckDate();
        UserIO.printMessage(VehicleIO.DROP_OFF_DATE_MESSAGE);
        LocalDate dropOffDate = VehicleIO.getCorrectCheckDate();
        if (dropOffDate.isBefore(pickUpDate)) {
            UserIO.printMessage(RentalDetailsIO.DROP_OFF_NOT_BEFORE_PICK_UP);
            dropOffDate = VehicleIO.getCorrectCheckDate();
        }
        BigDecimal totalNett = rentalDetailsService.getTotalNett(pickUpDate, dropOffDate, vehicle);
        BigDecimal totalGross = rentalDetailsService.getTotalGross(totalNett);
        BigDecimal totalTax = rentalDetailsService.getTotalTax(pickUpDate, dropOffDate);
        rentalDetailsService.create(new RentalDetails(
                vehicle.getId(),
                customer.getId(),
                employee.getId(),
                pickUpDate,
                dropOffDate,
                totalNett,
                totalGross,
                totalTax,
                LocalDate.now()
        ));
        UserIO.printMessage(RentalDetailsIO.FINAL_MESSAGE);
        RentalDetails rentalDetails = rentalDetailsService.get(commonDAO.getLastInsertId());
        List<RentalDetails> rentalDetailsToDisplay = new ArrayList<>();
        rentalDetailsToDisplay.add(rentalDetails);
        displayTable(prepareRowsTableRentalDetails(rentalDetailsToDisplay), prepareHeaderTableRentalDetails());
    }

    private static void performEmployeeDeactivation(EmployeeService employeeService) {
        try {
            UserIO.printMessage(EmployeeIO.SHOW_ALL_EMPLOYEES_MESSAGE);
            displayTable(prepareRowsTableEmployee(employeeService.getALl()), prepareHeaderTableEmployee());
            UserIO.printMessage(EmployeeIO.DEACTIVATE_INITIAL_MESSAGE);
            Long enteredId = EmployeeIO.getCorrectCheckIdEmployeeUpdate(employeeService);
            Employee employee = employeeService.get(enteredId);
            if (employee.getRemoveDateTime() != null) {
                UserIO.printMessage(EmployeeIO.DEACTIVATE_NOT_ACTIVE_MESSAGE);
            } else {
                employee.setRemoveDateTime(LocalDate.now());
                employeeService.update(employee);
                List<Employee> employeesUpdated = new ArrayList<>();
                employeesUpdated.add(employee);
                displayTable(prepareRowsTableEmployee(employeesUpdated), prepareHeaderTableEmployee());
                UserIO.printMessage(EmployeeIO.DEACTIVATE_FINAL_MESSAGE);
            }
        } catch (EmployeeNotFoundException e) {
            UserIO.printMessage(EmployeeIO.FIND_FINAL_NEGATIVE);
        }
    }

    private static void performEmployeeFind(EmployeeService employeeService) {
        try {
            UserIO.printMessage(EmployeeIO.SHOW_ALL_EMPLOYEES_MESSAGE);
            displayTable(prepareRowsTableEmployee(employeeService.getALl()), prepareHeaderTableEmployee());
            int firstItemNumber = MenuFindEmployee.values()[0].getItemNumber();
            int lastItemMenu = MenuFindEmployee.values().length;
            UserIO.printMessage(EmployeeIO.FIND_MAIN_MESSAGE);
            Integer enteredNumber = getValidMenuChoice(firstItemNumber, lastItemMenu);
            Employee employee = prepareChosenEmployee(enteredNumber);
            List<Employee> employees = employeeService.find(employee);
            UserIO.printMessage(EmployeeIO.FIND_FINAL);
            displayTable(prepareRowsTableEmployee(employees), prepareHeaderTableEmployee());
        } catch (EmployeeNotFoundException e) {
            UserIO.printMessage(EmployeeIO.FIND_FINAL_NEGATIVE);
        } catch (ParseException e) {
            UserIO.printMessage(UserIO.PARSE_ERROR);
        }
    }

    private static void performEmployeeUpdate(EmployeeService employeeService) {
        try {
            UserIO.printMessage(EmployeeIO.SHOW_ALL_EMPLOYEES_MESSAGE);
            displayTable(prepareRowsTableEmployee(employeeService.getALl()), prepareHeaderTableEmployee());
            int firstItemValue = MenuUpdateEmployee.values()[0].getItemName();
            int lastItemValue = MenuUpdateEmployee.values().length;
            UserIO.printMessage(EmployeeIO.UPDATE_INITIAL_MESSAGE);
            Long correctCheckIdEmployeeUpdate = EmployeeIO.getCorrectCheckIdEmployeeUpdate(employeeService);
            Employee employee = employeeService.get(correctCheckIdEmployeeUpdate);
            UserIO.printMessage(EmployeeIO.UPDATE_MAIN_MESSAGE);
            int enteredNumber = getValidMenuChoice(firstItemValue, lastItemValue);
            updateEmployeeField(employee, enteredNumber);
            employeeService.update(employee);
            List<Employee> employeesUpdated = new ArrayList<>();
            employeesUpdated.add(employee);
            displayTable(prepareRowsTableEmployee(employeesUpdated), prepareHeaderTableEmployee());
            UserIO.printMessage(EmployeeIO.UPDATE_FINAL_MESSAGE);
        } catch (EmployeeNotFoundException e) {
            UserIO.printMessage(EmployeeIO.UPDATE_NEGATIVE_MESSAGE);
        } catch (NumberFormatException e) {
            UserIO.printMessage(EmployeeIO.FIND_NEGATIVE_MESSAGE);
        }
    }

    private static void performEmployeeCreation(EmployeeService employeeService, CommonDAO commonDAO) {
        try {
            UserIO.printMessage(EmployeeIO.CREATE_INITIAL_MESSAGE);
            employeeService.create(new Employee(
                    EmployeeIO.getCorrectCheckFirstName(),
                    EmployeeIO.getCorrectCheckLastName(),
                    EmployeeIO.getCorrectCheckPersonalIdNumber(),
                    EmployeeIO.getCorrectCheckCity(),
                    EmployeeIO.getCorrectCheckPostalCode(),
                    EmployeeIO.getCorrectCheckStreet(),
                    EmployeeIO.getCorrectCheckStreetNumber(),
                    EmployeeIO.getCorrectCheckApartmentNumber()
            ));
            Employee employee = employeeService.get(commonDAO.getLastInsertId());
            List<Employee> employees = new ArrayList<>();
            employees.add(employee);
            displayTable(prepareRowsTableEmployee(employees), prepareHeaderTableEmployee());
            UserIO.printMessage(EmployeeIO.CREATE_FINAL_POSITIVE);
        } catch (EmployeeNotFoundException e) {
            UserIO.printMessage(EmployeeIO.CREATE_FINAL_NEGATIVE);
        }
    }

    private static void performVehicleDeactivation(VehicleService vehicleService) {
        try {
            UserIO.printMessage(VehicleIO.SHOW_ALL_VEHICLES_MESSAGE);
            displayTable(prepareRowsTableVehicle(vehicleService.getAll()), prepareHeaderTableVehicle());
            UserIO.printMessage(VehicleIO.DEACTIVATE_INITIAL_MESSAGE);
            Long enteredId = VehicleIO.getCorrectCheckIdVehicleUpdate(vehicleService);
            Vehicle vehicle = vehicleService.get(enteredId);
            if (vehicle.getRemoveDateTime() != null) {
                UserIO.printMessage(VehicleIO.DEACTIVATE_NOT_ACTIVE_MESSAGE);
            } else {
                vehicle.setRemoveDateTime(LocalDate.now());
                vehicleService.update(vehicle);
                List<Vehicle> vehicles = new ArrayList<>();
                vehicles.add(vehicle);
                displayTable(prepareRowsTableVehicle(vehicles), prepareHeaderTableVehicle());
                UserIO.printMessage(VehicleIO.DEACTIVATE_FINAL_MESSAGE);
            }
        } catch (VehicleNotFoundException e) {
            UserIO.printMessage(VehicleIO.NEGATIVE_UPDATE);
        }
    }

    private static void performVehicleFind(VehicleService vehicleService) {
        try {
            int firstMenuItem = MenuFindVehicle.values()[0].getMenuItem();
            int lastMenuItem = MenuFindVehicle.values().length;
            UserIO.printMessage(VehicleIO.SHOW_ALL_VEHICLES_MESSAGE);
            displayTable(prepareRowsTableVehicle(vehicleService.getAll()), prepareHeaderTableVehicle());
            UserIO.printMessage(VehicleIO.FIND_MAIN_MESSAGE);
            Integer enteredNumber = getValidMenuChoice(firstMenuItem, lastMenuItem);
            Vehicle vehicle = prepareChosenVehicle(enteredNumber);
            List<Vehicle> vehicles = vehicleService.find(vehicle);
            UserIO.printMessage(VehicleIO.FIND_FINAL);
            displayTable(prepareRowsTableVehicle(vehicles), prepareHeaderTableVehicle());
        } catch (VehicleNotFoundException e) {
            UserIO.printMessage(VehicleIO.FIND_FINAL_NEGATIVE);
        }
    }

    private static void performVehicleUpdate(VehicleService vehicleService, VehicleRateStatsService vehicleRateStatsService) {
        int firstItemValue = MenuUpdateVehicle.values()[0].getItemName();
        int lastItemValue = MenuUpdateVehicle.values().length;
        try {
            UserIO.printMessage(VehicleIO.SHOW_ALL_VEHICLES_MESSAGE);
            displayTable(prepareRowsTableVehicle(vehicleService.getAll()), prepareHeaderTableVehicle());
            UserIO.printMessage(VehicleIO.UPDATE_INITIAL_MESSAGE);
            Vehicle vehicle = vehicleService.get(VehicleIO.getCorrectCheckIdVehicleUpdate(vehicleService));
            UserIO.printMessage(VehicleIO.UPDATE_MAIN_MESSAGE);
            Integer enteredNumber = getValidMenuChoice(firstItemValue, lastItemValue);
            if (enteredNumber.equals(MenuUpdateVehicle.NEW_CURRENT_RATE_NETT.getItemName())) {
                vehicleRateStatsService.create(new VehicleRateStats(
                        vehicle.getId(),
                        vehicle.getCurrentRateNett(),
                        vehicle.getCurrentRateGross(),
                        vehicle.getCurrentTaxPercent(),
                        LocalDate.now()
                ));
            }
            updateVehicleField(vehicle, enteredNumber);
            vehicleService.update(vehicle);
            List<Vehicle> vehicles = new ArrayList<>();
            vehicles.add(vehicle);
            displayTable(prepareRowsTableVehicle(vehicles), prepareHeaderTableVehicle());
            UserIO.printMessage(VehicleIO.FINAL_UPDATE_MESSAGE);
        } catch (VehicleNotFoundException e) {
            UserIO.printMessage(VehicleIO.NEGATIVE_UPDATE);
        }
    }

    private static void updateVehicleField(Vehicle vehicle, Integer enteredNumber) {
        switch (MenuUpdateVehicle.prepareMenuUpdateVehicle(enteredNumber)) {
            case NEW_REGISTRATION_NUMBER -> vehicle.setRegistrationNumber(VehicleIO.getCorrectCheckUpdateRegistrationNumber());
            case NEW_BRAND -> vehicle.setBrand(VehicleIO.getCorrectCheckUpdateBrand());
            case NEW_MODEL -> vehicle.setModel(VehicleIO.getCorrectCheckUpdateModel());
            case NEW_VIN_NUMBER -> vehicle.setVinNumber(VehicleIO.getCorrectCheckUpdateVinNumber());
            case NEW_CURRENT_RATE_NETT -> {
                BigDecimal correctCheckUpdateCurrentRateNett = VehicleIO.getCorrectCheckUpdateCurrentRateNett();
                vehicle.setCurrentRateNett(correctCheckUpdateCurrentRateNett);
                vehicle.setCurrentRateGross(correctCheckUpdateCurrentRateNett.multiply(VehicleService.TAX_LEVEL));
            }
            case NEW_CURRENT_TAX_LEVEL -> vehicle.setCurrentTaxPercent(VehicleIO.getCorrectCheckUpdateTaxLevel());
        }
    }

    private static void updateEmployeeField(Employee employee, Integer enteredNumber) {
        switch (MenuUpdateEmployee.prepareMenuUpdateEmployeeItem(enteredNumber)) {
            case NEW_FIRST_NAME -> employee.setFirstName(EmployeeIO.getCorrectCheckUpdateFirstName());
            case NEW_LAST_NAME -> employee.setLastName(EmployeeIO.getCorrectCheckUpdateLastName());
            case NEW_PERSONAL_ID_NUMBER -> employee.setPersonalIdNumber(EmployeeIO.getCorrectCheckUpdatePersonalIdNumber());
            case NEW_CITY -> employee.setCity(EmployeeIO.getCorrectCheckUpdateCity());
            case NEW_POSTAL_CODE -> employee.setPostalCode(EmployeeIO.getCorrectCheckUpdatePostalCode());
            case NEW_STREET_NAME -> employee.setStreetName(EmployeeIO.getCorrectCheckUpdateStreet());
            case NEW_STREET_NUMBER -> employee.setStreetNumber(EmployeeIO.getCorrectCheckUpdateStreetNumber());
            case NEW_APARTMENT_NUMBER -> employee.setApartmentNumber(EmployeeIO.getCorrectCheckUpdateApartmentNumber());
        }
    }

    private static void performVehicleCreation(VehicleService vehicleService, CommonDAO commonDAO) {
        try {
            UserIO.printMessage(VehicleIO.CREATE_INITIAL_MESSAGE);
            String correctCheckRegistrationNumber = VehicleIO.getCorrectCheckRegistrationNumber();
            String correctCheckBrand = VehicleIO.getCorrectCheckBrand();
            String correctCheckModel = VehicleIO.getCorrectCheckModel();
            String correctCheckVinNumber = VehicleIO.getCorrectCheckVinNumber();
            BigDecimal correctCheckCurrentRateNett = VehicleIO.getCorrectCheckCurrentRateNett();
            BigDecimal correctCheckCurrentRateGross = correctCheckCurrentRateNett.multiply(VehicleService.TAX_LEVEL);
            BigDecimal correctCheckCurrentTaxLevel = VehicleService.TAX_LEVEL;
            vehicleService.create(new Vehicle(
                    correctCheckRegistrationNumber,
                    correctCheckBrand,
                    correctCheckModel,
                    correctCheckVinNumber,
                    correctCheckCurrentRateNett,
                    correctCheckCurrentRateGross,
                    correctCheckCurrentTaxLevel
            ));
            Vehicle vehicle = vehicleService.get(commonDAO.getLastInsertId());
            List<Vehicle> vehicles = new ArrayList<>();
            vehicles.add(vehicle);
            displayTable(prepareRowsTableVehicle(vehicles), prepareHeaderTableVehicle());
        } catch (VehicleNotFoundException e) {
            UserIO.printMessage(VehicleIO.NEGATIVE_FIND);
        }
        UserIO.printMessage(VehicleIO.FINAL_CREATE_MESSAGE);
    }

    private static void performCustomerDeactivation(CustomerService customerService) {
        UserIO.printMessage(CustomerIO.SHOW_ALL_CUSTOMERS_MESSAGE);
        displayTable(prepareRowsTableCustomer(customerService.getAll()), prepareHeaderTableCustomer());
        UserIO.printMessage(CustomerIO.DEACTIVATE_INITIAL_MESSAGE);
        try {
            Long enteredId = CustomerIO.getCorrectCheckIdCustomerUpdate(customerService);
            Customer customer = customerService.get(enteredId);
            if (customer.getRemoveDateTime() != null) {
                UserIO.printMessage(CustomerIO.DEACTIVATE_NOT_ACTIVE_MESSAGE);
            } else {
                customer.setRemoveDateTime(LocalDate.now());
                customerService.update(customer);
                List<Customer> customersToDisplay = new ArrayList<>();
                customersToDisplay.add(customer);
                displayTable(prepareRowsTableCustomer(customersToDisplay), prepareHeaderTableCustomer());
                UserIO.printMessage(CustomerIO.DEACTIVATE_FINAL_MESSAGE);
            }
        } catch (CustomerNotFoundException e) {
            UserIO.printMessage(CustomerIO.FIND_FINAL_NEGATIVE);
        }
    }

    private static void performCustomerUpdate(CustomerService customerService) {
        int firstItemValue = MenuUpdateCustomer.values()[0].getItemName();
        int lastItemValue = MenuUpdateCustomer.values().length;
        try {
            UserIO.printMessage(CustomerIO.SHOW_ALL_CUSTOMERS_MESSAGE);
            displayTable(prepareRowsTableCustomer(customerService.getAll()), prepareHeaderTableCustomer());
            UserIO.printMessage(CustomerIO.UPDATE_INITIAL_MESSAGE);
            Customer customer = customerService.get(CustomerIO.getCorrectCheckIdCustomerUpdate(customerService));
            UserIO.printMessage(CustomerIO.UPDATE_MAIN_MESSAGE);
            int enteredNumber = getValidMenuChoice(firstItemValue, lastItemValue);
            updateCustomerField(customer, enteredNumber);
            customerService.update(customer);
            List<Customer> customers = new ArrayList<>();
            customers.add(customer);
            displayTable(prepareRowsTableCustomer(customers), prepareHeaderTableCustomer());
        } catch (CustomerNotFoundException e) {
            UserIO.printMessage(CustomerIO.FIND_FINAL_NEGATIVE);
        }
        UserIO.printMessage(CustomerIO.UPDATE_FINAL_MESSAGE);
    }

    private static void displayTable(List<Row> rows, Header header) {
        TablePrinter tablePrinter = new TablePrinter(new Table(header, rows));
        tablePrinter.print();
    }

    private static void performFindCustomer(CustomerService customerService) {
        try {
            int firstItemValue = MenuFindCustomer.values()[0].getItemNumber();
            int lastItemValue = MenuFindCustomer.values().length;
            UserIO.printMessage(CustomerIO.SHOW_ALL_CUSTOMERS_MESSAGE);
            displayTable(prepareRowsTableCustomer(customerService.getAll()), prepareHeaderTableCustomer());
            UserIO.printMessage(CustomerIO.FIND_MAIN_MESSAGE);
            Integer enteredNumber = getValidMenuChoice(firstItemValue, lastItemValue);
            Customer preparedCustomer = prepareChosenCustomer(enteredNumber);
            List<Customer> customers = customerService.find(preparedCustomer);
            UserIO.printMessage(CustomerIO.FIND_FINAL);
            displayTable(prepareRowsTableCustomer(customers), prepareHeaderTableCustomer());
        } catch (CustomerNotFoundException e) {
            UserIO.printMessage(CustomerIO.FIND_FINAL_NEGATIVE);
        }
    }

    private static void performCustomerCreation(CustomerService customerService, CommonDAO commonDAO) {
        try {
            UserIO.printMessage(CustomerIO.CREATE_INITIAL_MESSAGE);
            customerService.create(new Customer(
                    CustomerIO.getCorrectCheckFirstName(),
                    CustomerIO.getCorrectCheckLastName(),
                    CustomerIO.getCorrectCheckPersonalIdNumber(),
                    CustomerIO.getCorrectCheckCity(),
                    CustomerIO.getCorrectCheckPostalCode(),
                    CustomerIO.getCorrectCheckStreet(),
                    CustomerIO.getCorrectCheckStreetNumber(),
                    CustomerIO.getCorrectCheckApartmentNumber(),
                    CustomerIO.getCorrectCheckCompanyName(),
                    CustomerIO.getCorrectCheckNIPNumber(),
                    CustomerIO.getCorrectCheckCompanyCity(),
                    CustomerIO.getCorrectCheckCompanyPostalCode(),
                    CustomerIO.getCorrectCheckCompanyStreet(),
                    CustomerIO.getCorrectCheckCompanyStreetNumber(),
                    CustomerIO.getCorrectCheckCompanyApartmentNumber()
            ));
            Customer customer = customerService.get(commonDAO.getLastInsertId());
            List<Customer> customers = new ArrayList<>();
            customers.add(customer);
            displayTable(prepareRowsTableCustomer(customers), prepareHeaderTableCustomer());
            UserIO.printMessage(CustomerIO.FINAL_CREATE_MESSAGE);
        } catch (CustomerNotFoundException e) {
            UserIO.printMessage(CustomerIO.FIND_FINAL_NEGATIVE);
        } catch (IllegalArgumentException e) {
            UserIO.printMessage(UserIO.FINAL_MESSAGE_MAIN);
        }
    }

    private static List<Row> prepareRowsTableCustomer(List<Customer> customers) {
        List<Row> rows = new ArrayList<>();
        for (Customer customer : customers) {
            Row row = new Row();
            row.addColumn(new RowColumn(customer.getId().toString()));
            row.addColumn(new RowColumn(customer.getFirstName()));
            row.addColumn(new RowColumn(customer.getLastName()));
            row.addColumn(new RowColumn(customer.getPersonalIdNumber()));
            row.addColumn(new RowColumn(customer.getCity()));
            row.addColumn(new RowColumn(customer.getPostalCode()));
            row.addColumn(new RowColumn(customer.getStreet()));
            row.addColumn(new RowColumn(customer.getStreetNumber().toString()));
            row.addColumn(customer.getApartmentNumber() == null ? new RowColumn() : new RowColumn(customer.getApartmentNumber().toString()));
            row.addColumn(customer.getCompanyName() == null ? new RowColumn() : new RowColumn(customer.getCompanyName()));
            row.addColumn(customer.getTaxIdNumber() == null ? new RowColumn() : new RowColumn(customer.getTaxIdNumber()));
            row.addColumn(customer.getCompanyCity() == null ? new RowColumn() : new RowColumn(customer.getCompanyCity()));
            row.addColumn(customer.getCompanyPostalCode() == null ? new RowColumn() : new RowColumn(customer.getCompanyPostalCode()));
            row.addColumn(customer.getCompanyStreet() == null ? new RowColumn() : new RowColumn(customer.getCompanyStreet()));
            row.addColumn(customer.getCompanyStreetNumber() == null ? new RowColumn() : new RowColumn(customer.getCompanyStreetNumber().toString()));
            row.addColumn(customer.getCompanyApartmentNumber() == null ? new RowColumn() : new RowColumn(customer.getCompanyApartmentNumber().toString()));
            row.addColumn(customer.getRemoveDateTime() == null ? new RowColumn() : new RowColumn(customer.getRemoveDateTime().toString()));
            rows.add(row);
        }
        if (rows.isEmpty()) throw new CustomerNotFoundException();
        return rows;
    }

    private static List<Row> prepareRowsTableVehicle(List<Vehicle> vehicles) {
        List<Row> rows = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            Row row = new Row();
            row.addColumn(new RowColumn(vehicle.getId().toString()));
            row.addColumn(new RowColumn(vehicle.getRegistrationNumber()));
            row.addColumn(new RowColumn(vehicle.getBrand()));
            row.addColumn(new RowColumn(vehicle.getModel()));
            row.addColumn(new RowColumn(vehicle.getVinNumber()));
            row.addColumn(new RowColumn(vehicle.getCurrentRateNett().toString()));
            row.addColumn(new RowColumn(vehicle.getCurrentRateGross().toString()));
            row.addColumn(new RowColumn(vehicle.getCurrentTaxPercent().toString()));
            row.addColumn(new RowColumn(vehicle.getRemoveDateTime() == null ? null : vehicle.getRemoveDateTime().toString()));
            rows.add(row);
        }
        if (rows.isEmpty()) throw new CustomerNotFoundException();
        return rows;
    }

    private static List<Row> prepareRowsTableEmployee(List<Employee> employees) {
        List<Row> rows = new ArrayList<>();
        for (Employee employee : employees) {
            Row row = new Row();
            row.addColumn(new RowColumn(employee.getId().toString()));
            row.addColumn(new RowColumn(employee.getFirstName()));
            row.addColumn(new RowColumn(employee.getLastName()));
            row.addColumn(new RowColumn(employee.getPersonalIdNumber()));
            row.addColumn(new RowColumn(employee.getCity()));
            row.addColumn(new RowColumn(employee.getPostalCode()));
            row.addColumn(new RowColumn(employee.getStreetName()));
            row.addColumn(new RowColumn(employee.getStreetNumber().toString()));
            row.addColumn(employee.getApartmentNumber() == null ? new RowColumn() : new RowColumn(employee.getApartmentNumber().toString()));
            row.addColumn(employee.getRemoveDateTime() == null ? new RowColumn() : new RowColumn(employee.getRemoveDateTime().toString()));
            rows.add(row);
        }
        if (rows.isEmpty()) throw new EmployeeNotFoundException();
        return rows;
    }

    private static List<Row> prepareRowsTableRentalDetails(List<RentalDetails> rentalDetails) {
        List<Row> rows = new ArrayList<>();
        for (RentalDetails rentalDetail : rentalDetails) {
            Row row = new Row();
            row.addColumn(new RowColumn(rentalDetail.getId().toString()));
            row.addColumn(new RowColumn(rentalDetail.getVehicleId().toString()));
            row.addColumn(new RowColumn(rentalDetail.getCustomerId().toString()));
            row.addColumn(new RowColumn(rentalDetail.getEmployeeId().toString()));
            row.addColumn(new RowColumn(rentalDetail.getPickUp().toString()));
            row.addColumn(new RowColumn(rentalDetail.getDropOff().toString()));
            row.addColumn(new RowColumn(rentalDetail.getTotalRateNett().toString()));
            row.addColumn(new RowColumn(rentalDetail.getTotalRateGross().toString()));
            row.addColumn(new RowColumn(rentalDetail.getTotalTaxPercent().toString()));
            row.addColumn(new RowColumn(rentalDetail.getCreateDateTime().toString()));
            rows.add(row);
        }
        if (rows.isEmpty()) throw new RentalDetailsNotFoundException();
        return rows;
    }

    private static List<Row> prepareRowsTableVehicleRateChange(List<VehicleRateStats> vehicleRateStats) {
        List<Row> rows = new ArrayList<>();
        for (VehicleRateStats vehicleRateStatsElement : vehicleRateStats) {
            Row row = new Row();
            row.addColumn(new RowColumn(vehicleRateStatsElement.getId().toString()));
            row.addColumn(new RowColumn(vehicleRateStatsElement.getVehicleId().toString()));
            row.addColumn(new RowColumn(vehicleRateStatsElement.getRateNett().toString()));
            row.addColumn(new RowColumn(vehicleRateStatsElement.getRateGross().toString()));
            row.addColumn(new RowColumn(vehicleRateStatsElement.getTaxPercent().toString()));
            row.addColumn(new RowColumn(vehicleRateStatsElement.getRateChangeDate().toString()));
            rows.add(row);
        }
        if (rows.isEmpty()) throw new RentalDetailsNotFoundException();
        return rows;
    }

    private static List<Row> prepareRowsTableTopEarnersByMonthReport(List<TopEarnersByMonth> topEarnersByMonths) {
        List<Row> rows = new ArrayList<>();
        for (TopEarnersByMonth topEarnersByMonth : topEarnersByMonths) {
            Row row = new Row();
            row.addColumn(new RowColumn(topEarnersByMonth.getId().toString()));
            row.addColumn(new RowColumn(topEarnersByMonth.getRegistrationNumber()));
            row.addColumn(new RowColumn(topEarnersByMonth.getBrand()));
            row.addColumn(new RowColumn(topEarnersByMonth.getTotalRevenue().toString()));
            row.addColumn(new RowColumn(topEarnersByMonth.getDropOffMonth().toString()));
            row.addColumn(new RowColumn(topEarnersByMonth.getGetDropOffYear().toString()));
            rows.add(row);
        }
        if (rows.isEmpty()) throw new RentalDetailsNotFoundException();
        return rows;
    }

    private static List<Row> prepareRowsTableTopEarnersByYearReport(List<TopEarnersByYear> topEarnersByYears) {
        List<Row> rows = new ArrayList<>();
        for (TopEarnersByYear topEarnersByYear : topEarnersByYears) {
            Row row = new Row();
            row.addColumn(new RowColumn(topEarnersByYear.getId().toString()));
            row.addColumn(new RowColumn(topEarnersByYear.getRegistrationNumber()));
            row.addColumn(new RowColumn(topEarnersByYear.getBrand()));
            row.addColumn(new RowColumn(topEarnersByYear.getTotalRevenue().toString()));
            row.addColumn(new RowColumn(topEarnersByYear.getGetDropOffYear().toString()));
            rows.add(row);
        }
        if (rows.isEmpty()) throw new RentalDetailsNotFoundException();
        return rows;
    }

    private static List<Row> prepareRowsTableTopEarnersByRentedDaysReport(List<TopEarnersByRentedDays> topEarnersByRentedDays) {
        List<Row> rows = new ArrayList<>();
        for (TopEarnersByRentedDays topEarnersByRentedDaysUnit : topEarnersByRentedDays) {
            Row row = new Row();
            row.addColumn(new RowColumn(topEarnersByRentedDaysUnit.getId().toString()));
            row.addColumn(new RowColumn(topEarnersByRentedDaysUnit.getBrand()));
            row.addColumn(new RowColumn(topEarnersByRentedDaysUnit.getRentedDays().toString()));
            row.addColumn(new RowColumn(topEarnersByRentedDaysUnit.getNotRentedDays().toString()));
            row.addColumn(new RowColumn(topEarnersByRentedDaysUnit.getTotalRev().toString()));
            row.addColumn(new RowColumn(topEarnersByRentedDaysUnit.getRevPerRentedDay().toString()));
            row.addColumn(new RowColumn(topEarnersByRentedDaysUnit.getRentalsNumber().toString()));
            row.addColumn(new RowColumn(topEarnersByRentedDaysUnit.getYear().toString()));
            rows.add(row);
        }
        if (rows.isEmpty()) throw new RentalDetailsNotFoundException();
        return rows;
    }

    private static Header prepareHeaderTableTopEarnersByRentedDaysReport() {
        Header header = new Header();
        header.addColumn(new HeaderColumn("ID"));
        header.addColumn(new HeaderColumn("Brand"));
        header.addColumn(new HeaderColumn("Rented Days"));
        header.addColumn(new HeaderColumn("Not Rented Days"));
        header.addColumn(new HeaderColumn("Total Revenue"));
        header.addColumn(new HeaderColumn("Revenue per Rented Day"));
        header.addColumn(new HeaderColumn("Rentals Number"));
        header.addColumn(new HeaderColumn("Year"));
        return header;
    }

    private static Header prepareHeaderTableTopEarnersByYearReport() {
        Header header = new Header();
        header.addColumn(new HeaderColumn("ID"));
        header.addColumn(new HeaderColumn("Registration Number"));
        header.addColumn(new HeaderColumn("Brand"));
        header.addColumn(new HeaderColumn("Total Revenue"));
        header.addColumn(new HeaderColumn("Drop Off Year"));
        return header;
    }

    private static Header prepareHeaderTableTopEarnersByMonthReport() {
        Header header = new Header();
        header.addColumn(new HeaderColumn("ID"));
        header.addColumn(new HeaderColumn("Registration Number"));
        header.addColumn(new HeaderColumn("Brand"));
        header.addColumn(new HeaderColumn("Total Revenue"));
        header.addColumn(new HeaderColumn("Drop Off Month"));
        header.addColumn(new HeaderColumn("Drop Off Year"));
        return header;
    }

    private static Header prepareHeaderTableVehicleRateChange() {
        Header header = new Header();
        header.addColumn(new HeaderColumn("ID"));
        header.addColumn(new HeaderColumn("Vehicle ID"));
        header.addColumn(new HeaderColumn("Rate Nett"));
        header.addColumn(new HeaderColumn("Rate Gross"));
        header.addColumn(new HeaderColumn("Tax Percent"));
        header.addColumn(new HeaderColumn("Rate Change Date"));
        return header;
    }

    private static Header prepareHeaderTableRentalDetails() {
        Header header = new Header();
        header.addColumn(new HeaderColumn("ID"));
        header.addColumn(new HeaderColumn("Vehicle ID"));
        header.addColumn(new HeaderColumn("Customer ID"));
        header.addColumn(new HeaderColumn("Employee ID"));
        header.addColumn(new HeaderColumn("PickUp Date"));
        header.addColumn(new HeaderColumn("DropOff Date"));
        header.addColumn(new HeaderColumn("Total Rental Nett"));
        header.addColumn(new HeaderColumn("Total Rental Gross"));
        header.addColumn(new HeaderColumn("Total Rental Tax"));
        header.addColumn(new HeaderColumn("Create Date"));
        return header;
    }

    private static Header prepareHeaderTableEmployee() {
        Header header = new Header();
        header.addColumn(new HeaderColumn("ID"));
        header.addColumn(new HeaderColumn("First Name"));
        header.addColumn(new HeaderColumn("Last Name"));
        header.addColumn(new HeaderColumn("Personal ID Number"));
        header.addColumn(new HeaderColumn("City"));
        header.addColumn(new HeaderColumn("Postal Code"));
        header.addColumn(new HeaderColumn("Street"));
        header.addColumn(new HeaderColumn("Street Number"));
        header.addColumn(new HeaderColumn("Apartment Number"));
        header.addColumn(new HeaderColumn("Remove Time"));
        return header;
    }

    private static Header prepareHeaderTableVehicle() {
        Header header = new Header();
        header.addColumn(new HeaderColumn("ID"));
        header.addColumn(new HeaderColumn("Registration Number"));
        header.addColumn(new HeaderColumn("Brand"));
        header.addColumn(new HeaderColumn("Model"));
        header.addColumn(new HeaderColumn("VIN Number"));
        header.addColumn(new HeaderColumn("Current Rate Nett"));
        header.addColumn(new HeaderColumn("Current Rate Gross"));
        header.addColumn(new HeaderColumn("Current Tax Percent"));
        header.addColumn(new HeaderColumn("Remove Time"));
        return header;
    }

    private static Header prepareHeaderTableCustomer() {
        Header header = new Header();
        header.addColumn(new HeaderColumn("ID"));
        header.addColumn(new HeaderColumn("First Name"));
        header.addColumn(new HeaderColumn("Last Name"));
        header.addColumn(new HeaderColumn("Personal ID Number"));
        header.addColumn(new HeaderColumn("City"));
        header.addColumn(new HeaderColumn("Postal Code"));
        header.addColumn(new HeaderColumn("Street"));
        header.addColumn(new HeaderColumn("Street Number"));
        header.addColumn(new HeaderColumn("Apartment Number"));
        header.addColumn(new HeaderColumn("Company Name"));
        header.addColumn(new HeaderColumn("Company NIP Number"));
        header.addColumn(new HeaderColumn("Company City"));
        header.addColumn(new HeaderColumn("Company Postal Code"));
        header.addColumn(new HeaderColumn("Company Street"));
        header.addColumn(new HeaderColumn("Company Street Number"));
        header.addColumn(new HeaderColumn("Company Apartment Number"));
        header.addColumn(new HeaderColumn("Remove Time"));
        return header;
    }

    public static Vehicle prepareChosenVehicle(Integer number) {
        Long id = null;
        String registrationNumber = null;
        String brand = null;
        String model = null;
        String vinNumber = null;
        BigDecimal currentRateNett = null;
        BigDecimal currentRateGross = null;
        BigDecimal currentTaxPercent = null;
        LocalDate removeDateTime = null;
        switch (MenuFindVehicle.prepareMenuFindVehicleItem(number)) {
            case ID -> id = VehicleIO.getCorrectCheckIdFind();
            case REGISTRATION_NUMBER -> registrationNumber = VehicleIO.getCorrectCheckRegistrationNumberFind();
            case BRAND -> brand = VehicleIO.getCorrectCheckBrandFind();
            case MODEL -> model = VehicleIO.getCorrectCheckModelFind();
            case VIN_NUMBER -> vinNumber = VehicleIO.getCorrectCheckVinNumberFind();
            case RATE_NETT -> currentRateNett = VehicleIO.getCorrectCheckCurrentRateNettFind();
            case RATE_GROSS -> currentRateGross = VehicleIO.getCorrectCheckCurrentRateGrossFind();
            case TAX_LEVEL -> currentTaxPercent = VehicleIO.getCorrectCheckCurrentTaxPercentFind();
            case DEACTIVATION_DATE -> removeDateTime = UserIO.getCorrectCheckDate();
        }
        return new Vehicle(
                id, registrationNumber, brand, model, vinNumber, currentRateNett, currentRateGross, currentTaxPercent, removeDateTime
        );
    }

    public static RentalDetails prepareChosenRentalDetails(Integer number) {
        Long id = null;
        Long vehicleId = null;
        Long customerId = null;
        Long employeeId = null;
        LocalDate pickUp = null;
        LocalDate dropOff = null;
        BigDecimal totalRateNett = null;
        BigDecimal totalRateGross = null;
        BigDecimal totalTaxPercent = null;
        LocalDate createDateTime = null;
        switch (MenuFindRentalDetails.prepareMenuFindRentalDetailsItem(number)) {
            case ID -> id = RentalDetailsIO.getCorrectCheckIdRentalDetails();
            case VEHICLE_ID -> vehicleId = RentalDetailsIO.getCorrectCheckIdVehicleFindRentalDetails();
            case CUSTOMER_ID -> customerId = RentalDetailsIO.getCorrectCheckIdCustomerFindRentalDetails();
            case EMPLOYEE_ID -> employeeId = RentalDetailsIO.getCorrectCheckIdEmployeeFindRentalDetails();
            case PICK_UP_DATE -> pickUp = UserIO.getCorrectCheckDate();
            case DROP_OFF_DATE -> dropOff = UserIO.getCorrectCheckDate();
            case TOTAL_RATE_NETT -> totalRateNett = UserIO.getCorrectCheckCurrentRate();
            case TOTAL_RATE_GROSS -> totalRateGross = UserIO.getCorrectCheckCurrentRate();
            case TOTAL_TAX -> totalTaxPercent = UserIO.getCorrectCheckCurrentRate();
            case CREATE_TIME -> createDateTime = UserIO.getCorrectCheckDate();
        }
        return new RentalDetails(
                id, vehicleId, customerId, employeeId, pickUp, dropOff, totalRateNett, totalRateGross, totalTaxPercent, createDateTime
        );
    }

    public static Customer prepareChosenCustomer(Integer number) {
        Long id = null;
        String firstName = null;
        String lastName = null;
        String personalIdNumber = null;
        String city = null;
        String postalCode = null;
        String streetName = null;
        Integer streetNumber = null;
        Integer apartmentNumber = null;
        String companyName = null;
        String taxIdNumber = null;
        String companyCity = null;
        String companyPostalCode = null;
        String companyStreetName = null;
        Integer companyStreetNumber = null;
        Integer companyApartmentNumber = null;
        LocalDate removeDateTime = null;
        switch (MenuFindCustomer.prepareMenuFindCustomerItem(number)) {
            case ID -> id = CustomerIO.getIdToFind();
            case FIRST_NAME -> firstName = CustomerIO.getFirstNameToFind();
            case LAST_NAME -> lastName = CustomerIO.getLastNameToFind();
            case PERSONAL_ID_NUMBER -> personalIdNumber = CustomerIO.getPersonalIdNumberToFind();
            case CITY -> city = CustomerIO.getCityToFind();
            case POSTAL_CODE -> postalCode = CustomerIO.getPostalCodeToFind();
            case STREET_NAME -> streetName = CustomerIO.getStreetToFind();
            case STREET_NUMBER -> streetNumber = CustomerIO.getStreetNumberToFind();
            case APARTMENT_NUMBER -> apartmentNumber = CustomerIO.getApartmentNumberToFind();
            case COMPANY_NAME -> companyName = CustomerIO.getCompanyNameToFind();
            case TAX_ID_NUMBER -> taxIdNumber = CustomerIO.getNIPNumberToFind();
            case COMPANY_CITY -> companyCity = CustomerIO.getCompanyCityToFind();
            case COMPANY_POSTAL_CODE -> companyPostalCode = CustomerIO.getCompanyPostalCodeToFind();
            case COMPANY_STREET -> companyStreetName = CustomerIO.getCompanyStreetToFind();
            case COMPANY_STREET_NUMBER -> companyApartmentNumber = CustomerIO.getCompanyApartmentNumberToFind();
            case COMPANY_APARTMENT_NUMBER -> companyStreetNumber = CustomerIO.getCompanyStreetNumberToFind();
            case DEACTIVATION_DATE -> removeDateTime = UserIO.getCorrectCheckDate();
        }
        return new Customer(
                id, firstName, lastName, personalIdNumber, city, postalCode, streetName, streetNumber, apartmentNumber, companyName, taxIdNumber, companyCity, companyPostalCode, companyStreetName, companyStreetNumber, companyApartmentNumber, removeDateTime
        );
    }

    public static Employee prepareChosenEmployee(Integer number) throws ParseException {
        Long id = null;
        String firstName = null;
        String lastName = null;
        String personalIdNumber = null;
        String city = null;
        String postalCode = null;
        String streetName = null;
        Integer streetNumber = null;
        Integer apartmentNumber = null;
        LocalDate removeDateTime = null;
        switch (MenuFindEmployee.prepareMenuFindEmployeeItem(number)) {
            case ID -> id = EmployeeIO.getCorrectCheckIdFind();
            case FIRST_NAME -> firstName = EmployeeIO.getCorrectCheckFirstNameFind();
            case LAST_NAME -> lastName = EmployeeIO.getCorrectCheckLastNameFind();
            case PERSONAL_ID_NUMBER -> personalIdNumber = EmployeeIO.getCorrectCheckPersonalIdNumberFind();
            case CITY -> city = EmployeeIO.getCorrectCheckCityFind();
            case POSTAL_CODE -> postalCode = EmployeeIO.getCorrectCheckPostalCodeFind();
            case STREET_NAME -> streetName = EmployeeIO.getCorrectCheckStreetFind();
            case STREET_NUMBER -> streetNumber = EmployeeIO.getCorrectCheckStreetNumberFind();
            case APARTMENT_NUMBER -> apartmentNumber = EmployeeIO.getCorrectCheckApartmentNumberFind();
            case DEACTIVATION_DATE -> removeDateTime = UserIO.getCorrectCheckDate();
        }
        return new Employee(
                id, firstName, lastName, personalIdNumber, city, postalCode, streetName, streetNumber, apartmentNumber, removeDateTime
        );
    }

    public static VehicleRateStats prepareChosenVehicleRateStats(Integer number) {
        Long id = null;
        Long vehicleId = null;
        LocalDate rateChangeDate = null;
        BigDecimal rateNett = null;
        BigDecimal rateGross = null;
        BigDecimal taxPercent = null;
        switch (MenuFindVehicleRateStats.prepareMenuFindVehicleRateStatsItem(number)) {
            case ID -> id = VehicleRateStatsIO.getCorrectCheckIdFindVehicleRateStats();
            case VEHICLE_ID -> vehicleId = VehicleRateStatsIO.getCorrectCheckVehicleIdFindVehicleRateStats();
            case RATE_CHANGE_DATE -> rateChangeDate = VehicleRateStatsIO.getCorrectCheckRateChangeDateFindVehicleRateStats();
            case RATE_NETT_BEFORE_CHANGE -> rateNett = VehicleRateStatsIO.getCorrectCheckRateFindVehicleRateStats();
            case RATE_GROSS_BEFORE_CHANGE -> rateGross = VehicleRateStatsIO.getCorrectCheckRateFindVehicleRateStats();
            case TAX_BEFORE_CHANGE -> taxPercent = VehicleRateStatsIO.getCorrectCheckRateFindVehicleRateStats();
        }
        return new VehicleRateStats(
                id, vehicleId, rateNett, rateGross, taxPercent, rateChangeDate
        );
    }

    public static void updateCustomerField(Customer customerToEdit, Integer enteredFieldToChange) {
        switch (MenuUpdateCustomer.prepareMenuUpdateCustomerItem(enteredFieldToChange)) {
            case NEW_FIRST_NAME -> customerToEdit.setFirstName(CustomerIO.getCorrectCheckUpdateFirstName());
            case NEW_LAST_NAME -> customerToEdit.setLastName(CustomerIO.getCorrectCheckUpdateLastName());
            case NEW_PERSONAL_ID_NUMBER -> customerToEdit.setPersonalIdNumber(CustomerIO.getCorrectCheckUpdatePersonalIdNumber());
            case NEW_CITY -> customerToEdit.setCity(CustomerIO.getCorrectCheckUpdateCity());
            case NEW_POSTAL_CODE -> customerToEdit.setPostalCode(CustomerIO.getCorrectCheckUpdatePostalCode());
            case NEW_STREET_NAME -> customerToEdit.setStreet(CustomerIO.getCorrectCheckUpdateStreet());
            case NEW_STREET_NUMBER -> customerToEdit.setStreetNumber(CustomerIO.getCorrectCheckUpdateStreetNumber());
            case NEW_APARTMENT_NUMBER -> customerToEdit.setApartmentNumber(CustomerIO.getCorrectCheckUpdateApartmentNumber());
            case NEW_COMPANY_NAME -> customerToEdit.setCompanyName(CustomerIO.getCorrectCheckUpdateCompanyName());
            case NEW_TAX_ID_NUMBER -> customerToEdit.setTaxIdNumber(CustomerIO.getCorrectCheckUpdateTaxIdNumber());
            case NEW_COMPANY_CITY -> customerToEdit.setCompanyCity(CustomerIO.getCorrectCheckUpdateCompanyCity());
            case NEW_COMPANY_POSTAL_CODE -> customerToEdit.setCompanyPostalCode(CustomerIO.getCorrectCheckUpdateCompanyPostalCode());
            case NEW_COMPANY_STREET -> customerToEdit.setCompanyStreet(CustomerIO.getCorrectCheckUpdateCompanyStreet());
            case NEW_COMPANY_STREET_NUMBER -> customerToEdit.setCompanyStreetNumber(CustomerIO.getCorrectCheckUpdateCompanyStreetNumber());
            case NEW_COMPANY_APARTMENT_NUMBER -> customerToEdit.setCompanyApartmentNumber(CustomerIO.getCorrectCheckUpdateCompanyApartmentNumber());
        }
    }

    private static Integer getValidMenuChoice(int minNumber, int maxNumber) {
        boolean shouldContinue;
        Integer correctNumber;
        do {
            shouldContinue = false;
            correctNumber = getCorrectNumber();
            if (correctNumber > maxNumber || correctNumber < minNumber) {
                UserIO.printMessage(UserIO.CORRECT_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return correctNumber;
    }

    private static Integer getCorrectNumber() {
        boolean shouldContinue;
        Integer number = null;
        do {
            shouldContinue = false;
            try {
                number = UserIO.getEnteredNumber();
                Validator.validateNumber(number);
            } catch (ValidationException | NumberFormatException e) {
                UserIO.printMessage(UserIO.CORRECT_NUMBER);
                shouldContinue = true;
            }
        } while (shouldContinue);
        return number;
    }


}
