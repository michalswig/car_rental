package com.company.service;

import com.company.exception.DataBaseException;
import com.company.dto.Vehicle;
import com.company.dao.VehicleDAO;

import java.math.BigDecimal;
import java.util.List;

public class VehicleService {
    public static BigDecimal TAX_LEVEL = new BigDecimal("1.20");

    private final VehicleDAO vehicleDAO;

    public VehicleService(VehicleDAO vehicleDAO) {
        this.vehicleDAO = vehicleDAO;
    }

    public Vehicle get(Long id) {
        return vehicleDAO.get(id).get();
    }

    public List<Vehicle> getAll() {
        return vehicleDAO.getAll();
    }

    public void create(Vehicle vehicle) throws DataBaseException {
        createVehicle(vehicle);
    }

    public void update(Vehicle vehicle) throws DataBaseException {
        vehicleDAO.update(vehicle);
    }

    public static void setTaxLevel(BigDecimal taxLevel) {
        TAX_LEVEL = taxLevel;
    }

    public List<Vehicle> find(Vehicle vehicle) {
        return vehicleDAO.find(vehicle);
    }

    private void createVehicle(Vehicle vehicle) {
        vehicleDAO.create(new Vehicle(
                vehicle.getRegistrationNumber(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getVinNumber(),
                vehicle.getCurrentRateNett(),
                vehicle.getCurrentRateGross(),
                vehicle.getCurrentTaxPercent()
        ));
    }


}
