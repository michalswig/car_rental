package com.company.dao;

import com.company.exception.DataBaseException;
import com.company.dto.VehicleRateStats;
import com.company.exception.VehicleRateStatsNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleRateStatsDAO {
    private final static String GET = "SELECT * FROM VEHICLE_RATE_STATS WHERE ID=?";
    private final static String GET_ALL = "SELECT * FROM VEHICLE_RATE_STATS";
    private final static String CREATE = "INSERT INTO VEHICLE_RATE_STATS (VEHICLE_ID, RATE_NETT_BEFORE_CHANGE, RATE_GROSS_BEFORE_CHANGE, TAX_PERCENT_BEFORE_CHANGE, RATE_CHANGE_DATE) VALUES (?,?,?,?,?)";
    private final static String UPDATE = "UPDATE vehicle_rate_stats SET VEHICLE_ID=?, RATE_NETT_BEFORE_CHANGE=?, RATE_GROSS_BEFORE_CHANGE=?, TAX_PERCENT_BEFORE_CHANGE=?, RATE_CHANGE_DATE=? WHERE ID=?";
    private Connection connection;

    public VehicleRateStatsDAO(Connection connection) {
        this.connection = connection;
    }

    public Optional<VehicleRateStats> get(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(prepareVehicleRateStat(resultSet));
            } else {
                throw new VehicleRateStatsNotFoundException();
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
    }

    public List<VehicleRateStats> getAll() {
        List<VehicleRateStats> vehicleRateStats = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                VehicleRateStats vehicleRateStatsUnit = prepareVehicleRateStat(resultSet);
                vehicleRateStats.add(vehicleRateStatsUnit);
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
        if(vehicleRateStats.isEmpty()) throw new VehicleRateStatsNotFoundException();
        return vehicleRateStats;
    }

    public void create(VehicleRateStats vehicleRateStats) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setLong(1, vehicleRateStats.getVehicleId());
            preparedStatement.setBigDecimal(2, vehicleRateStats.getRateNett());
            preparedStatement.setBigDecimal(3, vehicleRateStats.getRateGross());
            preparedStatement.setBigDecimal(4, vehicleRateStats.getTaxPercent());
            preparedStatement.setDate(5, Date.valueOf(vehicleRateStats.getRateChangeDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
    }

    public void update(VehicleRateStats vehicleRateStats) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setLong(1, vehicleRateStats.getVehicleId());
            preparedStatement.setBigDecimal(2, vehicleRateStats.getRateNett());
            preparedStatement.setBigDecimal(3, vehicleRateStats.getRateGross());
            preparedStatement.setBigDecimal(4, vehicleRateStats.getTaxPercent());
            preparedStatement.setDate(5, Date.valueOf(vehicleRateStats.getRateChangeDate()));
            preparedStatement.setLong(6, vehicleRateStats.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
    }

    public List<VehicleRateStats> find(VehicleRateStats findVehicleRateStats) {
        List<VehicleRateStats> vehicleRateStats = new ArrayList<>();
        int parameterIndexCounter = 0;
        String findQuery = getQuery(findVehicleRateStats, parameterIndexCounter);
        try (PreparedStatement preparedStatement = connection.prepareStatement(findQuery)) {
            prepareQueryParams(findVehicleRateStats, parameterIndexCounter, preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                VehicleRateStats vehicleRateStat = prepareVehicleRateStat(resultSet);
                vehicleRateStats.add(vehicleRateStat);
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
        if (vehicleRateStats.isEmpty()) throw new VehicleRateStatsNotFoundException();
        return vehicleRateStats;
    }

    private VehicleRateStats prepareVehicleRateStat(ResultSet resultSet) {
        try {
            VehicleRateStats vehicleRateStat = new VehicleRateStats(
                    resultSet.getLong("ID"),
                    resultSet.getLong("VEHICLE_ID"),
                    resultSet.getBigDecimal("RATE_NETT_BEFORE_CHANGE"),
                    resultSet.getBigDecimal("RATE_GROSS_BEFORE_CHANGE"),
                    resultSet.getBigDecimal("TAX_PERCENT_BEFORE_CHANGE"),
                    resultSet.getDate("RATE_CHANGE_DATE").toLocalDate()
            );
            return vehicleRateStat;
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }

    }

    private void prepareQueryParams(VehicleRateStats findVehicleRateStats, int parameterIndexCounter, PreparedStatement preparedStatement) {
        try {
            if (findVehicleRateStats.getId() != null) {
                parameterIndexCounter++;
                preparedStatement.setLong(parameterIndexCounter, findVehicleRateStats.getId());
            }
            if (findVehicleRateStats.getVehicleId() != null) {
                parameterIndexCounter++;
                preparedStatement.setLong(parameterIndexCounter, findVehicleRateStats.getVehicleId());
            }
            if (findVehicleRateStats.getRateNett() != null) {
                parameterIndexCounter++;
                preparedStatement.setBigDecimal(parameterIndexCounter, findVehicleRateStats.getRateNett());
            }
            if (findVehicleRateStats.getRateGross() != null) {
                parameterIndexCounter++;
                preparedStatement.setBigDecimal(parameterIndexCounter, findVehicleRateStats.getRateGross());
            }
            if (findVehicleRateStats.getTaxPercent() != null) {
                parameterIndexCounter++;
                preparedStatement.setBigDecimal(parameterIndexCounter, findVehicleRateStats.getTaxPercent());
            }
            if (findVehicleRateStats.getRateChangeDate() != null) {
                parameterIndexCounter++;
                preparedStatement.setDate(parameterIndexCounter, Date.valueOf(findVehicleRateStats.getRateChangeDate()));
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }

    }

    private String getQuery(VehicleRateStats findVehicleRateStats, int parameterIndexCounter) {
        String findQuery = "SELECT * FROM VEHICLE_RATE_STATS WHERE ";

        if (findVehicleRateStats.getId() != null) {
            parameterIndexCounter++;
            findQuery += "ID=?";
        }
        if (findVehicleRateStats.getVehicleId() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "VEHICLE_ID=?";
        }
        if (findVehicleRateStats.getRateNett() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "RATE_NETT_BEFORE_CHANGE=?";
        }
        if (findVehicleRateStats.getRateGross() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "RATE_GROSS_BEFORE_CHANGE=?";
        }
        if (findVehicleRateStats.getTaxPercent() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "TAX_PERCENT_BEFORE_CHANGE=?";
        }
        if (findVehicleRateStats.getRateChangeDate() != null) {
            parameterIndexCounter++;
            if (parameterIndexCounter > 1) findQuery += " AND ";
            findQuery += "RATE_CHANGE_DATE=?";
        }
        return findQuery;
    }

}
