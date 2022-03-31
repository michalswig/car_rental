package com.company.dao;

import com.company.dto.Customer;
import com.company.exception.DataBaseException;
import com.company.exception.CustomerNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    private final static String GET = "SELECT * FROM CUSTOMER WHERE ID=?";
    private final static String GET_ALL = "SELECT * FROM CUSTOMER";
    private final static String CREATE = "INSERT INTO CUSTOMER(FIRST_NAME, LAST_NAME, PERSONAL_ID_NUMBER, CITY, POSTAL_CODE, STREET, STREET_NUMBER, APARTMENT_NUMBER, COMPANY_NAME, TAX_ID_NUMBER, COMPANY_CITY, COMPANY_POSTAL_CODE, COMPANY_STREET, COMPANY_STREET_NUMBER, COMPANY_APARTMENT_NUMBER) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final static String UPDATE = "UPDATE CUSTOMER SET FIRST_NAME=?, LAST_NAME=?, PERSONAL_ID_NUMBER=?, CITY=?, POSTAL_CODE=?, STREET=?, STREET_NUMBER=?, APARTMENT_NUMBER=?,  COMPANY_NAME=?, TAX_ID_NUMBER=?, COMPANY_CITY=?, COMPANY_POSTAL_CODE=?, COMPANY_STREET=?,COMPANY_STREET_NUMBER=?, COMPANY_APARTMENT_NUMBER=?, REMOVE_DATETIME=? WHERE ID=?";


    private final Connection connection;

    public CustomerDAO(Connection connection) {
        this.connection = connection;
    }

    public Customer get(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return prepareCustomer(resultSet);
            } else {
                throw new CustomerNotFoundException();
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
    }

    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = prepareCustomer(resultSet);
                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
        if (customers.isEmpty()) throw new CustomerNotFoundException();
        return customers;
    }

    public void create(Customer customer) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getPersonalIdNumber());
            preparedStatement.setString(4, customer.getCity());
            preparedStatement.setString(5, customer.getPostalCode());
            preparedStatement.setString(6, customer.getStreet());
            preparedStatement.setInt(7, customer.getStreetNumber());
            if (customer.getApartmentNumber() == null) {
                preparedStatement.setNull(8, Types.INTEGER);
            } else {
                preparedStatement.setInt(8, customer.getApartmentNumber());
            }
            preparedStatement.setString(9, customer.getCompanyName() != null ? customer.getCompanyName() : null);
            preparedStatement.setString(10, customer.getTaxIdNumber() != null ? customer.getTaxIdNumber() : null);
            preparedStatement.setString(11, customer.getCompanyCity() != null ? customer.getCompanyCity() : null);
            preparedStatement.setString(12, customer.getCompanyPostalCode() != null ? customer.getCompanyPostalCode() : null);
            preparedStatement.setString(13, customer.getCompanyStreet() != null ? customer.getCompanyStreet() : null);
            if (customer.getCompanyStreetNumber() == null) {
                preparedStatement.setNull(14, Types.INTEGER);
            } else {
                preparedStatement.setInt(14, customer.getCompanyStreetNumber());
            }
            if (customer.getCompanyApartmentNumber() == null) {
                preparedStatement.setNull(15, Types.INTEGER);
            } else {
                preparedStatement.setInt(15, customer.getCompanyApartmentNumber());
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
    }

    public void update(Customer customer) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getPersonalIdNumber());
            preparedStatement.setString(4, customer.getCity());
            preparedStatement.setString(5, customer.getPostalCode());
            preparedStatement.setString(6, customer.getStreet());
            preparedStatement.setInt(7, customer.getStreetNumber());
            if (customer.getApartmentNumber() == null) {
                preparedStatement.setNull(8, Types.INTEGER);
            } else {
                preparedStatement.setInt(8, customer.getApartmentNumber());
            }
            preparedStatement.setString(9, customer.getCompanyName() != null ? customer.getCompanyName() : null);
            preparedStatement.setString(10, customer.getTaxIdNumber() != null ? customer.getTaxIdNumber() : null);
            preparedStatement.setString(11, customer.getCompanyCity() != null ? customer.getCompanyCity() : null);
            preparedStatement.setString(12, customer.getCompanyPostalCode() != null ? customer.getCompanyPostalCode() : null);
            preparedStatement.setString(13, customer.getCompanyStreet() != null ? customer.getCompanyStreet() : null);
            if (customer.getCompanyStreetNumber() == null) {
                preparedStatement.setNull(14, Types.INTEGER);
            } else {
                preparedStatement.setInt(14, customer.getCompanyStreetNumber());
            }
            if (customer.getCompanyApartmentNumber() == null) {
                preparedStatement.setNull(15, Types.INTEGER);
            } else {
                preparedStatement.setInt(15, customer.getCompanyApartmentNumber());
            }
            if (customer.getRemoveDateTime() != null) {
                preparedStatement.setDate(16, Date.valueOf(customer.getRemoveDateTime()));
            } else {
                preparedStatement.setNull(16, Types.DATE);
            }
            preparedStatement.setLong(17, customer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
    }

    public List<Customer> find(Customer findCustomer) {
        List<Customer> customers = new ArrayList<>();
        int parameterIndex = 0;
        String findQuery = getQuery(findCustomer, parameterIndex);
        try (PreparedStatement preparedStatement = connection.prepareStatement(findQuery)) {
            parameterIndex = 0;
            prepareQueryParams(findCustomer, parameterIndex, preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = prepareCustomer(resultSet);
                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
        if (customers.isEmpty()) throw new CustomerNotFoundException();
        return customers;
    }

    private Customer prepareCustomer(ResultSet resultSet) {
        try {
            Customer customer = new Customer(
                    resultSet.getLong("ID"),
                    resultSet.getString("FIRST_NAME"),
                    resultSet.getString("LAST_NAME"),
                    resultSet.getString("PERSONAL_ID_NUMBER"),
                    resultSet.getString("CITY"),
                    resultSet.getString("POSTAL_CODE"),
                    resultSet.getString("STREET"),
                    resultSet.getInt("STREET_NUMBER"),
                    resultSet.getInt("APARTMENT_NUMBER") != 0 ? resultSet.getInt("APARTMENT_NUMBER") : null,
                    resultSet.getString("COMPANY_NAME") != null ? resultSet.getString("COMPANY_NAME") : null,
                    resultSet.getString("TAX_ID_NUMBER") != null ? resultSet.getString("TAX_ID_NUMBER") : null,
                    resultSet.getString("COMPANY_CITY") != null ? resultSet.getString("COMPANY_CITY") : null,
                    resultSet.getString("COMPANY_POSTAL_CODE") != null ? resultSet.getString("COMPANY_POSTAL_CODE") : null,
                    resultSet.getString("COMPANY_STREET") != null ? resultSet.getString("COMPANY_STREET") : null,
                    resultSet.getInt("COMPANY_STREET_NUMBER") != 0 ? resultSet.getInt("COMPANY_STREET_NUMBER") : null,
                    resultSet.getInt("COMPANY_APARTMENT_NUMBER") != 0 ? resultSet.getInt("COMPANY_APARTMENT_NUMBER") : null,
                    resultSet.getDate("REMOVE_DATETIME") != null ? resultSet.getDate("REMOVE_DATETIME").toLocalDate() : null
            );
            return customer;
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
    }

    private void prepareQueryParams(Customer findCustomer, int parameterIndex, PreparedStatement preparedStatement) {
        try {
            if (findCustomer.getId() != null) {
                parameterIndex++;
                preparedStatement.setLong(parameterIndex, findCustomer.getId());
            }
            if (findCustomer.getFirstName() != null) {
                parameterIndex++;
                preparedStatement.setString(parameterIndex, findCustomer.getFirstName());
            }
            if (findCustomer.getLastName() != null) {
                parameterIndex++;
                preparedStatement.setString(parameterIndex, findCustomer.getLastName());
            }
            if (findCustomer.getPersonalIdNumber() != null) {
                parameterIndex++;
                preparedStatement.setString(parameterIndex, findCustomer.getPersonalIdNumber());
            }
            if (findCustomer.getCity() != null) {
                parameterIndex++;
                preparedStatement.setString(parameterIndex, findCustomer.getCity());
            }
            if (findCustomer.getPostalCode() != null) {
                parameterIndex++;
                preparedStatement.setString(parameterIndex, findCustomer.getPostalCode());
            }
            if (findCustomer.getStreet() != null) {
                parameterIndex++;
                preparedStatement.setString(parameterIndex, findCustomer.getStreet());
            }
            if (findCustomer.getStreetNumber() != null) {
                parameterIndex++;
                preparedStatement.setInt(parameterIndex, findCustomer.getStreetNumber());
            }
            if (findCustomer.getApartmentNumber() != null) {
                parameterIndex++;
                preparedStatement.setInt(parameterIndex, findCustomer.getApartmentNumber());
            }
            if (findCustomer.getCompanyName() != null) {
                parameterIndex++;
                preparedStatement.setString(parameterIndex, findCustomer.getCompanyName());
            }
            if (findCustomer.getTaxIdNumber() != null) {
                parameterIndex++;
                preparedStatement.setString(parameterIndex, findCustomer.getTaxIdNumber());
            }
            if (findCustomer.getCompanyCity() != null) {
                parameterIndex++;
                preparedStatement.setString(parameterIndex, findCustomer.getCompanyCity());
            }
            if (findCustomer.getCompanyPostalCode() != null) {
                parameterIndex++;
                preparedStatement.setString(parameterIndex, findCustomer.getCompanyPostalCode());
            }
            if (findCustomer.getCompanyStreet() != null) {
                parameterIndex++;
                preparedStatement.setString(parameterIndex, findCustomer.getCompanyStreet());
            }
            if (findCustomer.getCompanyStreetNumber() != null) {
                parameterIndex++;
                preparedStatement.setInt(parameterIndex, findCustomer.getCompanyStreetNumber());
            }
            if (findCustomer.getCompanyApartmentNumber() != null) {
                parameterIndex++;
                preparedStatement.setInt(parameterIndex, findCustomer.getCompanyApartmentNumber());
            }
            if (findCustomer.getRemoveDateTime() != null) {
                parameterIndex++;
                preparedStatement.setDate(parameterIndex, Date.valueOf(findCustomer.getRemoveDateTime()));
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
    }

    private String getQuery(Customer findCustomer, int parameterIndex) {
        String findQuery = "SELECT * FROM CUSTOMER WHERE ";
        if (findCustomer.getId() != null) {
            parameterIndex++;
            findQuery += "ID=?";
        }
        if (findCustomer.getFirstName() != null) {
            parameterIndex++;
            if (parameterIndex > 1) findQuery += " AND ";
            findQuery += "FIRST_NAME=?";
        }
        if (findCustomer.getLastName() != null) {
            parameterIndex++;
            if (parameterIndex > 1) findQuery += " AND ";
            findQuery += "LAST_NAME=?";
        }
        if (findCustomer.getPersonalIdNumber() != null) {
            parameterIndex++;
            if (parameterIndex > 1) findQuery += " AND ";
            findQuery += "PERSONAL_ID_NUMBER=?";
        }
        if (findCustomer.getCity() != null) {
            parameterIndex++;
            if (parameterIndex > 1) findQuery += " AND ";
            findQuery += "CITY=?";
        }
        if (findCustomer.getPostalCode() != null) {
            parameterIndex++;
            if (parameterIndex > 1) findQuery += " AND ";
            findQuery += "POSTAL_CODE=?";
        }
        if (findCustomer.getStreet() != null) {
            parameterIndex++;
            if (parameterIndex > 1) findQuery += " AND ";
            findQuery += "STREET=?";
        }
        if (findCustomer.getStreetNumber() != null) {
            parameterIndex++;
            if (parameterIndex > 1) findQuery += " AND ";
            findQuery += "STREET_NUMBER=?";
        }
        if (findCustomer.getApartmentNumber() != null) {
            parameterIndex++;
            if (parameterIndex > 1) findQuery += " AND ";
            findQuery += "APARTMENT_NUMBER=?";
        }
        if (findCustomer.getCompanyName() != null) {
            parameterIndex++;
            if (parameterIndex > 1) findQuery += " AND ";
            findQuery += "COMPANY_NAME=?";
        }
        if (findCustomer.getTaxIdNumber() != null) {
            parameterIndex++;
            if (parameterIndex > 1) findQuery += " AND ";
            findQuery += "TAX_ID_NUMBER=?";
        }
        if (findCustomer.getCompanyCity() != null) {
            parameterIndex++;
            if (parameterIndex > 1) findQuery += " AND ";
            findQuery += "COMPANY_CITY=?";
        }
        if (findCustomer.getCompanyPostalCode() != null) {
            parameterIndex++;
            if (parameterIndex > 1) findQuery += " AND ";
            findQuery += "COMPANY_POSTAL_CODE=?";
        }
        if (findCustomer.getCompanyStreet() != null) {
            parameterIndex++;
            if (parameterIndex > 1) findQuery += " AND ";
            findQuery += "COMPANY_STREET=?";
        }
        if (findCustomer.getCompanyStreetNumber() != null) {
            parameterIndex++;
            if (parameterIndex > 1) findQuery += " AND ";
            findQuery += "COMPANY_STREET_NUMBER=?";
        }
        if (findCustomer.getCompanyApartmentNumber() != null) {
            parameterIndex++;
            if (parameterIndex > 1) findQuery += " AND ";
            findQuery += "COMPANY_APARTMENT_NUMBER=?";
        }
        if (findCustomer.getRemoveDateTime() != null) {
            parameterIndex++;
            if (parameterIndex > 1) findQuery += " AND ";
            findQuery += "REMOVE_DATETIME=?";
        }
        return findQuery;
    }

}














