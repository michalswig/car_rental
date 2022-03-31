package com.company.service;

import com.company.exception.DataBaseException;
import com.company.dto.RentalDetails;
import com.company.dto.Vehicle;
import com.company.dao.RentalDetailsDAO;
import com.company.exception.RentalDetailsNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class RentalDetailsService {

    private final RentalDetailsDAO rentalDetailsDAO;

    public RentalDetailsService(RentalDetailsDAO rentalDetailsDAO) {
        this.rentalDetailsDAO = rentalDetailsDAO;
    }

    public BigDecimal getTotalNett(LocalDate pickUp, LocalDate dropOff, Vehicle vehicle) {
        return BigDecimal.valueOf(DAYS.between(pickUp, dropOff)).multiply(vehicle.getCurrentRateNett());
    }

    public BigDecimal getTotalGross(BigDecimal totalNett) {
        return totalNett.multiply(VehicleService.TAX_LEVEL);
    }

    public BigDecimal getTotalTax(LocalDate pickUp, LocalDate dropOff) {
        return VehicleService.TAX_LEVEL.multiply(BigDecimal.valueOf(DAYS.between(pickUp, dropOff)));
    }


    public void create(RentalDetails rentalDetails) throws DataBaseException {
        rentalDetailsDAO.create(new RentalDetails(
                rentalDetails.getVehicleId(),
                rentalDetails.getCustomerId(),
                rentalDetails.getEmployeeId(),
                rentalDetails.getPickUp(),
                rentalDetails.getDropOff(),
                rentalDetails.getTotalRateNett(),
                rentalDetails.getTotalRateGross(),
                rentalDetails.getTotalTaxPercent(),
                rentalDetails.getCreateDateTime()
        ));
    }

    public RentalDetails get(Long id)  {
        if (rentalDetailsDAO.get(id).isEmpty()) throw new RentalDetailsNotFoundException();
        else return rentalDetailsDAO.get(id).get();
    }

    public List<RentalDetails> getAll()  {
        if (rentalDetailsDAO.getAll().isEmpty()) throw new RentalDetailsNotFoundException();
        else return rentalDetailsDAO.getAll();
    }

    public List<RentalDetails> find(RentalDetails rentalDetails){
        return rentalDetailsDAO.find(rentalDetails);
    }


}
