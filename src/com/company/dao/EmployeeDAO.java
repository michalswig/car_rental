package com.company.dao;

import com.company.exception.DataBaseException;
import com.company.dto.Employee;
import com.company.exception.EmployeeNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDAO {
    private final static String GET = "SELECT * FROM EMPLOYEE WHERE ID=?";
    private final static String GET_ALL = "SELECT * FROM EMPLOYEE";
    private final static String CREATE = "INSERT INTO EMPLOYEE(FIRST_NAME, LAST_NAME, PERSONAL_ID_NUMBER, CITY, POSTAL_CODE, STREET, STREET_NUMBER, APARTMENT_NUMBER) VALUES (?,?,?,?,?,?,?,?)";
    private final static String UPDATE = "UPDATE EMPLOYEE SET FIRST_NAME=?, LAST_NAME=?, PERSONAL_ID_NUMBER=?, CITY=?, POSTAL_CODE=?, STREET=?, STREET_NUMBER=?, APARTMENT_NUMBER=?, REMOVE_DATETIME=? WHERE ID=?";
    private final static String DELETE = "DELETE FROM EMPLOYEE WHERE ID=?";

    private Connection connection;

    public EmployeeDAO(Connection connection) {
        this.connection = connection;
    }

    public Optional<Employee> get(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return
                        Optional.of(prepareEmployee(resultSet));
            } else {
                throw new EmployeeNotFoundException();
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
    }

    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = prepareEmployee(resultSet);
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
        if (employees.isEmpty()) throw new EmployeeNotFoundException();
        return employees;
    }

    public void create(Employee employee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getPersonalIdNumber());
            preparedStatement.setString(4, employee.getCity());
            preparedStatement.setString(5, employee.getPostalCode());
            preparedStatement.setString(6, employee.getStreetName());
            preparedStatement.setInt(7, employee.getStreetNumber());
            if (employee.getApartmentNumber() == null) {
                preparedStatement.setNull(8, Types.INTEGER);
            } else {
                preparedStatement.setInt(8, employee.getApartmentNumber());
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
    }

    public void update(Employee employee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getPersonalIdNumber());
            preparedStatement.setString(4, employee.getCity());
            preparedStatement.setString(5, employee.getPostalCode());
            preparedStatement.setString(6, employee.getStreetName());
            preparedStatement.setInt(7, employee.getStreetNumber());
            if (employee.getApartmentNumber() == null) {
                preparedStatement.setNull(8, Types.INTEGER);
            } else {
                preparedStatement.setInt(8, employee.getApartmentNumber());
            }
            if (employee.getRemoveDateTime() != null) {
                preparedStatement.setDate(9, Date.valueOf(employee.getRemoveDateTime()));
            } else {
                preparedStatement.setNull(9, Types.DATE);
            }
            preparedStatement.setLong(10, employee.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
    }

    public List<Employee> find(Employee findEmployee) {
        List<Employee> employees = new ArrayList<>();
        int parameterIndexCounter = 0;
        String findQuery = getQuery(findEmployee, parameterIndexCounter);
        try (PreparedStatement preparedStatement = connection.prepareStatement(findQuery)) {
            parameterIndexCounter = 0;
            prepareQueryParams(findEmployee, parameterIndexCounter, preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = prepareEmployee(resultSet);
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
        return employees;
    }

    private Employee prepareEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee(
                resultSet.getLong("ID"),
                resultSet.getString("FIRST_NAME"),
                resultSet.getString("LAST_NAME"),
                resultSet.getString("PERSONAL_ID_NUMBER"),
                resultSet.getString("CITY"),
                resultSet.getString("POSTAL_CODE"),
                resultSet.getString("STREET"),
                resultSet.getInt("STREET_NUMBER"),
                resultSet.getInt("APARTMENT_NUMBER") != 0 ? resultSet.getInt("APARTMENT_NUMBER") : null,
                resultSet.getDate("REMOVE_DATETIME") != null ? resultSet.getDate("REMOVE_DATETIME").toLocalDate() : null
        );
        return employee;
    }

    private void prepareQueryParams(Employee findEmployee, int parameterIndexCounter, PreparedStatement preparedStatement) throws SQLException {
        if (findEmployee.getId() != null) {
            parameterIndexCounter++;
            preparedStatement.setLong(parameterIndexCounter, findEmployee.getId());
        }
        if (findEmployee.getFirstName() != null) {
            parameterIndexCounter++;
            preparedStatement.setString(parameterIndexCounter, findEmployee.getFirstName());
        }
        if (findEmployee.getLastName() != null) {
            parameterIndexCounter++;
            preparedStatement.setString(parameterIndexCounter, findEmployee.getLastName());
        }
        if (findEmployee.getPersonalIdNumber() != null) {
            parameterIndexCounter++;
            preparedStatement.setString(parameterIndexCounter, findEmployee.getPersonalIdNumber());
        }
        if (findEmployee.getCity() != null) {
            parameterIndexCounter++;
            preparedStatement.setString(parameterIndexCounter, findEmployee.getCity());
        }
        if (findEmployee.getPostalCode() != null) {
            parameterIndexCounter++;
            preparedStatement.setString(parameterIndexCounter, findEmployee.getPostalCode());
        }
        if (findEmployee.getStreetName() != null) {
            parameterIndexCounter++;
            preparedStatement.setString(parameterIndexCounter, findEmployee.getStreetName());
        }
        if (findEmployee.getRemoveDateTime() != null) {
            parameterIndexCounter++;
            preparedStatement.setDate(parameterIndexCounter, Date.valueOf(findEmployee.getRemoveDateTime()));
        }
    }

    private String getQuery(Employee findEmployee, int parameterIndexCounter) {
        String findQuery = "SELECT * FROM EMPLOYEE WHERE ";
        if (findEmployee.getId() != null) {
            parameterIndexCounter++;
            findQuery += "ID=?";
        }
        if (findEmployee.getFirstName() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "FIRST_NAME=?";
        }
        if (findEmployee.getLastName() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "LAST_NAME=?";
        }
        if (findEmployee.getPersonalIdNumber() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "PERSONAL_ID_NUMBER=?";
        }
        if (findEmployee.getCity() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "CITY=?";
        }
        if (findEmployee.getPostalCode() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "POSTAL_CODE=?";
        }
        if (findEmployee.getStreetName() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "STREET=?";
        }
        if (findEmployee.getRemoveDateTime() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "REMOVE_DATETIME=?";
        }
        return findQuery;
    }

}
