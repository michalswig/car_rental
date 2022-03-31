package com.company.service;

import com.company.exception.DataBaseException;
import com.company.dto.VehicleRateStats;
import com.company.dao.VehicleRateStatsDAO;

import java.util.List;

public class VehicleRateStatsService {

    VehicleRateStatsDAO vehicleRateStatsDAO;

    public VehicleRateStatsService(VehicleRateStatsDAO vehicleRateStatsDAO) {
        this.vehicleRateStatsDAO = vehicleRateStatsDAO;
    }

    public void create(VehicleRateStats vehicleRateStats) throws DataBaseException {
        vehicleRateStatsDAO.create(new VehicleRateStats(
                vehicleRateStats.getVehicleId(),
                vehicleRateStats.getRateNett(),
                vehicleRateStats.getRateGross(),
                vehicleRateStats.getTaxPercent(),
                vehicleRateStats.getRateChangeDate()
        ));
    }

    public VehicleRateStats get(Long id) {
        return vehicleRateStatsDAO.get(id).get();
    }

    public List<VehicleRateStats> getAll() {
        return vehicleRateStatsDAO.getAll();
    }

    public List<VehicleRateStats> find(VehicleRateStats vehicleRateStats) {
        return vehicleRateStatsDAO.find(vehicleRateStats);
    }

    public void update(VehicleRateStats vehicleRateStats) {
        vehicleRateStatsDAO.update(vehicleRateStats);
    }


}
