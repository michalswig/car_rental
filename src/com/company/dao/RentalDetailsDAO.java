package com.company.dao;

import com.company.exception.DataBaseException;
import com.company.dto.RentalDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RentalDetailsDAO {
    private final static String GET = "SELECT * FROM RENTAL_DETAILS WHERE ID=?";
    private final static String GET_ALl = "SELECT * FROM RENTAL_DETAILS";
    private final static String UPDATE = "UPDATE RENTAL_DETAILS SET VEHICLE_ID=?, CUSTOMER_ID=?, EMPLOYEE_ID=?, PICK_UP=?, DROP_OFF=?, TOTAL_RATE_NETT=?, TOTAL_RATE_GROSS=?, TOTAL_TAX_PERCENT=?,  CREATE_DATETIME=? WHERE ID=?";
    private final static String CREATE = "INSERT INTO RENTAL_DETAILS(VEHICLE_ID, CUSTOMER_ID, EMPLOYEE_ID, PICK_UP, DROP_OFF, TOTAL_RATE_NETT, TOTAL_RATE_GROSS, TOTAL_TAX_PERCENT, CREATE_DATETIME) VALUES(?,?,?,?,?,?,?,?,?)";

    private final Connection connection;

    public RentalDetailsDAO(Connection connection) {
        this.connection = connection;
    }

    public Optional<RentalDetails> get(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return Optional.of(
                        createRentalDetails(resultSet)
                );
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
        return Optional.empty();
    }

    public List<RentalDetails> getAll() {
        List<RentalDetails> rentalDetailsToGet = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALl)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RentalDetails rentalDetails = createRentalDetails(resultSet);
                rentalDetailsToGet.add(rentalDetails);
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
        return rentalDetailsToGet;
    }

    public void update(RentalDetails rentalDetails) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setLong(1, rentalDetails.getVehicleId());
            preparedStatement.setLong(2, rentalDetails.getCustomerId());
            preparedStatement.setLong(3, rentalDetails.getEmployeeId());
            preparedStatement.setDate(4, Date.valueOf(rentalDetails.getPickUp()));
            preparedStatement.setDate(5, Date.valueOf(rentalDetails.getDropOff()));
            preparedStatement.setBigDecimal(6, rentalDetails.getTotalRateNett());
            preparedStatement.setBigDecimal(7, rentalDetails.getTotalRateGross());
            preparedStatement.setBigDecimal(8, rentalDetails.getTotalTaxPercent());
            preparedStatement.setDate(9, Date.valueOf(rentalDetails.getCreateDateTime()));
            preparedStatement.setLong(10, rentalDetails.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
    }

    public void create(RentalDetails rentalDetails) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setLong(1, rentalDetails.getVehicleId());
            preparedStatement.setLong(2, rentalDetails.getCustomerId());
            preparedStatement.setLong(3, rentalDetails.getEmployeeId());
            preparedStatement.setDate(4, Date.valueOf(rentalDetails.getPickUp()));
            preparedStatement.setDate(5, Date.valueOf(rentalDetails.getDropOff()));
            preparedStatement.setBigDecimal(6, rentalDetails.getTotalRateNett());
            preparedStatement.setBigDecimal(7, rentalDetails.getTotalRateGross());
            preparedStatement.setBigDecimal(8, rentalDetails.getTotalTaxPercent());
            preparedStatement.setDate(9, Date.valueOf(rentalDetails.getCreateDateTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
    }

    public List<RentalDetails> find(RentalDetails findRentalDetails) {
        List<RentalDetails> rentalDetails = new ArrayList<>();
        int parameterIndexCounter = 0;
        String findQuery = getQuery(findRentalDetails, parameterIndexCounter);
        try (PreparedStatement preparedStatement = connection.prepareStatement(findQuery)) {
            parameterIndexCounter = 0;
            prepareQueryParams(findRentalDetails, parameterIndexCounter, preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RentalDetails creationRentalDetails = createRentalDetails(resultSet);
                rentalDetails.add(creationRentalDetails);
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
        return rentalDetails;
    }

    private String getQuery(RentalDetails findRentalDetails, int parameterIndexCounter) {
        String findQuery = "SELECT * FROM RENTAL_DETAILS WHERE ";
        if (findRentalDetails.getId() != null) {
            parameterIndexCounter++;
            findQuery += "ID=?";
        }
        if (findRentalDetails.getVehicleId() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "VEHICLE_ID=?";
        }
        if (findRentalDetails.getCustomerId() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "CUSTOMER_ID=?";
        }
        if (findRentalDetails.getEmployeeId() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "EMPLOYEE_ID=?";
        }
        if (findRentalDetails.getPickUp() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "PICK_UP=?";
        }
        if (findRentalDetails.getDropOff() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "DROP_OFF=?";
        }
        if (findRentalDetails.getTotalRateNett() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "TOTAL_RATE_NETT=?";
        }
        if (findRentalDetails.getTotalRateGross() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "TOTAL_RATE_GROSS=?";
        }
        if (findRentalDetails.getTotalTaxPercent() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "TOTAL_TAX_PERCENT=?";
        }
        if (findRentalDetails.getCreateDateTime() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "CREATE_DATETIME=?";
        }
        return findQuery;
    }

    private RentalDetails createRentalDetails(ResultSet resultSet) throws SQLException {
        RentalDetails creationRentalDetails = new RentalDetails(
                resultSet.getLong("ID"),
                resultSet.getLong("VEHICLE_ID"),
                resultSet.getLong("CUSTOMER_ID"),
                resultSet.getLong("EMPLOYEE_ID"),
                resultSet.getDate("PICK_UP").toLocalDate(),
                resultSet.getDate("DROP_OFF").toLocalDate(),
                resultSet.getBigDecimal("TOTAL_RATE_NETT"),
                resultSet.getBigDecimal("TOTAL_RATE_GROSS"),
                resultSet.getBigDecimal("TOTAL_TAX_PERCENT"),
                resultSet.getDate("CREATE_DATETIME").toLocalDate()
        );
        return creationRentalDetails;
    }

    private void prepareQueryParams(RentalDetails findRentalDetails, int parameterIndexCounter, PreparedStatement preparedStatement) {
        try {
            if (findRentalDetails.getId() != null) {
                parameterIndexCounter++;
                preparedStatement.setLong(parameterIndexCounter, findRentalDetails.getId());
            }
            if (findRentalDetails.getVehicleId() != null) {
                parameterIndexCounter++;
                preparedStatement.setLong(parameterIndexCounter, findRentalDetails.getVehicleId());
            }
            if (findRentalDetails.getCustomerId() != null) {
                parameterIndexCounter++;
                preparedStatement.setLong(parameterIndexCounter, findRentalDetails.getCustomerId());
            }
            if (findRentalDetails.getEmployeeId() != null) {
                parameterIndexCounter++;
                preparedStatement.setLong(parameterIndexCounter, findRentalDetails.getEmployeeId());
            }
            if (findRentalDetails.getPickUp() != null) {
                parameterIndexCounter++;
                preparedStatement.setDate(parameterIndexCounter, Date.valueOf(findRentalDetails.getPickUp()));
            }
            if (findRentalDetails.getDropOff() != null) {
                parameterIndexCounter++;
                preparedStatement.setDate(parameterIndexCounter, Date.valueOf(findRentalDetails.getDropOff()));
            }
            if (findRentalDetails.getTotalRateNett() != null) {
                parameterIndexCounter++;
                preparedStatement.setBigDecimal(parameterIndexCounter, findRentalDetails.getTotalRateNett());
            }
            if (findRentalDetails.getTotalRateGross() != null) {
                parameterIndexCounter++;
                preparedStatement.setBigDecimal(parameterIndexCounter, findRentalDetails.getTotalRateGross());
            }
            if (findRentalDetails.getTotalTaxPercent() != null) {
                parameterIndexCounter++;
                preparedStatement.setBigDecimal(parameterIndexCounter, findRentalDetails.getTotalTaxPercent());
            }
            if (findRentalDetails.getCreateDateTime() != null) {
                parameterIndexCounter++;
                preparedStatement.setDate(parameterIndexCounter, Date.valueOf(findRentalDetails.getCreateDateTime()));
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
    }

}
