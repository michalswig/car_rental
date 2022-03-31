package com.company.dao;

import com.company.exception.DataBaseException;
import com.company.dto.Vehicle;
import com.company.exception.VehicleNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleDAO {

    private final static String GET = "SELECT * FROM VEHICLE WHERE ID=?";
    private final static String GET_ALL = "SELECT * FROM VEHICLE";
    private final static String CREATE = "INSERT INTO vehicle(REGISTRATION_NUMBER, BRAND, MODEL, VIN_NUMBER, CURRENT_RATE_NETT, CURRENT_RATE_GROSS, CURRENT_TAX_PERCENT) VALUES (?,?,?,?,?,?,?)";
    private final static String UPDATE = "UPDATE vehicle SET REGISTRATION_NUMBER=?, BRAND=?, MODEL=?, VIN_NUMBER=?, CURRENT_RATE_NETT=?, CURRENT_RATE_GROSS=?, CURRENT_TAX_PERCENT=?, REMOVE_DATETIME=? WHERE ID=?";

    private final Connection connection;

    public VehicleDAO(Connection connection) {
        this.connection = connection;
    }

    public Optional<Vehicle> get(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(
                        prepareVehicle(resultSet)
                );
            } else {
                throw new VehicleNotFoundException();
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
    }

    public List<Vehicle> getAll() {
        List<Vehicle> vehicles = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Vehicle vehicle = prepareVehicle(resultSet);
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
        if(vehicles.isEmpty()) throw new VehicleNotFoundException();
        return vehicles;
    }

    public void create(Vehicle vehicle) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setString(1, vehicle.getRegistrationNumber());
            preparedStatement.setString(2, vehicle.getBrand());
            preparedStatement.setString(3, vehicle.getModel());
            preparedStatement.setString(4, vehicle.getVinNumber());
            preparedStatement.setBigDecimal(5, vehicle.getCurrentRateNett());
            preparedStatement.setBigDecimal(6, vehicle.getCurrentRateGross());
            preparedStatement.setBigDecimal(7, vehicle.getCurrentTaxPercent());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
    }

    public void update(Vehicle vehicle) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, vehicle.getRegistrationNumber());
            preparedStatement.setString(2, vehicle.getBrand());
            preparedStatement.setString(3, vehicle.getModel());
            preparedStatement.setString(4, vehicle.getVinNumber());
            preparedStatement.setBigDecimal(5, vehicle.getCurrentRateNett());
            preparedStatement.setBigDecimal(6, vehicle.getCurrentRateGross());
            preparedStatement.setBigDecimal(7, vehicle.getCurrentTaxPercent());
            if (vehicle.getRemoveDateTime() != null) {
                preparedStatement.setDate(8, Date.valueOf(vehicle.getRemoveDateTime()));
            } else {
                preparedStatement.setNull(8, Types.DATE);
            }
            preparedStatement.setLong(9, vehicle.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
    }

    public List<Vehicle> find(Vehicle findVehicle) {
        List<Vehicle> vehicles = new ArrayList<>();
        int parameterIndexCounter = 0;
        String findQuery = getQuery(findVehicle, parameterIndexCounter);
        try (PreparedStatement preparedStatement = connection.prepareStatement(findQuery)) {
            parameterIndexCounter = 0;
            prepareQueryParams(findVehicle, parameterIndexCounter, preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Vehicle vehicle = prepareVehicle(resultSet);
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
        if (vehicles.isEmpty()) throw new VehicleNotFoundException();
        return vehicles;
    }

    private Vehicle prepareVehicle(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle(
                resultSet.getLong("ID"),
                resultSet.getString("REGISTRATION_NUMBER"),
                resultSet.getString("BRAND"),
                resultSet.getString("MODEL"),
                resultSet.getString("VIN_NUMBER"),
                resultSet.getBigDecimal("CURRENT_RATE_NETT"),
                resultSet.getBigDecimal("CURRENT_RATE_GROSS"),
                resultSet.getBigDecimal("CURRENT_TAX_PERCENT"),
                resultSet.getDate("REMOVE_DATETIME") != null ? resultSet.getDate("REMOVE_DATETIME").toLocalDate() : null
        );
        return vehicle;
    }

    private void prepareQueryParams(Vehicle findVehicle, int parameterIndexCounter, PreparedStatement preparedStatement) throws SQLException {
        if (findVehicle.getId() != null) {
            parameterIndexCounter++;
            preparedStatement.setLong(parameterIndexCounter, findVehicle.getId());
        }
        if (findVehicle.getRegistrationNumber() != null) {
            parameterIndexCounter++;
            preparedStatement.setString(parameterIndexCounter, findVehicle.getRegistrationNumber());
        }
        if (findVehicle.getBrand() != null) {
            parameterIndexCounter++;
            preparedStatement.setString(parameterIndexCounter, findVehicle.getBrand());
        }
        if (findVehicle.getModel() != null) {
            parameterIndexCounter++;
            preparedStatement.setString(parameterIndexCounter, findVehicle.getModel());
        }
        if (findVehicle.getVinNumber() != null) {
            parameterIndexCounter++;
            preparedStatement.setString(parameterIndexCounter, findVehicle.getVinNumber());
        }
        if (findVehicle.getCurrentRateNett() != null) {
            parameterIndexCounter++;
            preparedStatement.setBigDecimal(parameterIndexCounter, findVehicle.getCurrentRateNett());
        }
        if (findVehicle.getCurrentRateGross() != null) {
            parameterIndexCounter++;
            preparedStatement.setBigDecimal(parameterIndexCounter, findVehicle.getCurrentRateGross());
        }
        if (findVehicle.getCurrentTaxPercent() != null) {
            parameterIndexCounter++;
            preparedStatement.setBigDecimal(parameterIndexCounter, findVehicle.getCurrentTaxPercent());
        }
        if (findVehicle.getRemoveDateTime() != null) {
            parameterIndexCounter++;
            preparedStatement.setDate(parameterIndexCounter, Date.valueOf(findVehicle.getRemoveDateTime()));
        }
    }

    private String getQuery(Vehicle findVehicle, int parameterIndexCounter) {
        String findQuery = "SELECT * FROM VEHICLE WHERE ";
        if (findVehicle.getId() != null) {
            parameterIndexCounter++;
            findQuery += "ID=?";
        }
        if (findVehicle.getRegistrationNumber() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "REGISTRATION_NUMBER=?";
        }
        if (findVehicle.getBrand() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "BRAND=?";
        }
        if (findVehicle.getModel() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "MODEL=?";
        }
        if (findVehicle.getVinNumber() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "VIN_NUMBER=?";
        }
        if (findVehicle.getCurrentRateNett() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "CURRENT_RATE_NETT=?";
        }
        if (findVehicle.getCurrentRateGross() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "CURRENT_RATE_GROSS=?";
        }
        if (findVehicle.getCurrentTaxPercent() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "CURRENT_TAX_PERCENT=?";
        }
        if (findVehicle.getRemoveDateTime() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "RATE_CHANGE_DATE=?";
        }
        return findQuery;
    }

}
