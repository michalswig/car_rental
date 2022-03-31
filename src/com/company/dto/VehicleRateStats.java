package com.company.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class VehicleRateStats {
    private Long id;
    private Long vehicleId;
    private BigDecimal rateNett;
    private BigDecimal rateGross;
    private BigDecimal taxPercent;
    private LocalDate rateChangeDate;

    public VehicleRateStats(Long id, Long vehicleId, BigDecimal rateNett, BigDecimal rateGross, BigDecimal taxPercent, LocalDate rateChangeDate) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.rateNett = rateNett;
        this.rateGross = rateGross;
        this.taxPercent = taxPercent;
        this.rateChangeDate = rateChangeDate;
    }

    public VehicleRateStats(Long vehicleId, BigDecimal rateNett, BigDecimal rateGross, BigDecimal taxPercent, LocalDate rateChangeDate) {
        this.vehicleId = vehicleId;
        this.rateNett = rateNett;
        this.rateGross = rateGross;
        this.taxPercent = taxPercent;
        this.rateChangeDate = rateChangeDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public LocalDate getRateChangeDate() {
        return rateChangeDate;
    }

    public void setRateChangeDate(LocalDate rateChangeDate) {
        this.rateChangeDate = rateChangeDate;
    }

    public BigDecimal getRateNett() {
        return rateNett;
    }

    public void setRateNett(BigDecimal rateNett) {
        this.rateNett = rateNett;
    }

    public BigDecimal getRateGross() {
        return rateGross;
    }

    public void setRateGross(BigDecimal rateGross) {
        this.rateGross = rateGross;
    }

    public BigDecimal getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(BigDecimal taxPercent) {
        this.taxPercent = taxPercent;
    }

    @Override
    public String toString() {
        return "VehicleRateStats{" +
                "id=" + id +
                ", vehicleId=" + vehicleId +
                ", rateNett=" + rateNett +
                ", rateGross=" + rateGross +
                ", taxPercent=" + taxPercent +
                ", rateChangeDate=" + rateChangeDate +
                '}';
    }
}
