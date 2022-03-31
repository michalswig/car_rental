package com.company.dao;

import com.company.dto.TopEarnersByMonth;
import com.company.dto.TopEarnersByRentedDays;
import com.company.dto.TopEarnersByYear;
import com.company.exception.DataBaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CommonDAO {

    private final static String GET_LAST_INSERT_ID = "SELECT LAST_INSERT_ID()";

    private final static String GET_TOP_EARNERS_BY_MONTH_REPORT = "select VEHICLE_ID, REGISTRATION_NUMBER, BRAND, floor(sum(TOTAL_RATE_GROSS)) as RENT_REV, month(DROP_OFF) as MONTH_OF_RENT, year(DROP_OFF) as YEAR_OF_RENT from vehicle\n" +
            "    inner join rental_details rd on vehicle.ID = rd.VEHICLE_ID\n" +
            "    group by VEHICLE_ID, month(CREATE_DATETIME)\n" +
            "    order by DROP_OFF desc";

    private final static String GET_TOP_EARNERS_BY_YEAR_REPORT = "select VEHICLE_ID, REGISTRATION_NUMBER, BRAND, floor(sum(TOTAL_RATE_GROSS)) as RENT_REV, year(DROP_OFF) as YEAR_OF_RENT from vehicle\n" +
            "    inner join rental_details rd on vehicle.ID = rd.VEHICLE_ID\n" +
            "    group by VEHICLE_ID, year(DROP_OFF)\n" +
            "    order by RENT_REV desc";

    private final static String GET_TOP_EARNERS_BY_RENTED_DAYS = "select VEHICLE_ID, BRAND, datediff(DROP_OFF, PICK_UP) as RENTED_DAYS, " +
            "(360 - datediff(DROP_OFF, PICK_UP)) as UNRENTED_DAYS, CEILING(sum(TOTAL_RATE_NETT)) as REV, " +
            "FLOOR(CEILING(sum(TOTAL_RATE_NETT))/datediff(DROP_OFF, PICK_UP)) as REV_PER_RENT_DAY, COUNT(rental_details.ID), " +
            "YEAR(DROP_OFF) from rental_details join vehicle v on v.ID = rental_details.VEHICLE_ID group by VEHICLE_ID, " +
            "YEAR(DROP_OFF) order by REV_PER_RENT_DAY desc;";

    private final Connection connection;

    public CommonDAO(Connection connection) {
        this.connection = connection;
    }

    public Long getLastInsertId() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_LAST_INSERT_ID)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            } else {
                throw new NoSuchElementException();
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
    }

    public List<TopEarnersByMonth> getTopEarnersByMonthReport() {
        List<TopEarnersByMonth> topEarnersByMonths = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_TOP_EARNERS_BY_MONTH_REPORT)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TopEarnersByMonth topEarnersByMonth = new TopEarnersByMonth(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getBigDecimal(4),
                        resultSet.getLong(5),
                        resultSet.getLong(6)
                );
                topEarnersByMonths.add(topEarnersByMonth);
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
        return topEarnersByMonths;
    }

    public List<TopEarnersByYear> getTopEarnersByYearReport() {
        List<TopEarnersByYear> topEarnersByYears = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_TOP_EARNERS_BY_YEAR_REPORT)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TopEarnersByYear topEarnersByYear = new TopEarnersByYear(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getBigDecimal(4),
                        resultSet.getLong(5)
                );
                topEarnersByYears.add(topEarnersByYear);
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
        return topEarnersByYears;
    }

    public List<TopEarnersByRentedDays> getTopEarnersByRentedDaysReport() {
        List<TopEarnersByRentedDays> topEarnersByRentedDays = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_TOP_EARNERS_BY_RENTED_DAYS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TopEarnersByRentedDays topEarnersByRentedDaysUnit = new TopEarnersByRentedDays(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6),
                        resultSet.getInt(7),
                        resultSet.getLong(8)
                );
                topEarnersByRentedDays.add(topEarnersByRentedDaysUnit);
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage(), e);
        }
        return topEarnersByRentedDays;
    }

}
