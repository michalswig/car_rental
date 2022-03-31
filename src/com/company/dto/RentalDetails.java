package com.company.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RentalDetails {
    private Long id;
    private Long vehicleId;
    private Long customerId;
    private Long employeeId;
    private LocalDate pickUp;
    private LocalDate dropOff;
    private BigDecimal totalRateNett;
    private BigDecimal totalRateGross;
    private BigDecimal totalTaxPercent;
    private LocalDate createDateTime;

    public RentalDetails(Long id, Long vehicleId, Long customerId, Long employeeId, LocalDate pickUp, LocalDate dropOff, BigDecimal totalRateNett, BigDecimal totalRateGross, BigDecimal totalTaxPercent, LocalDate createDateTime) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.pickUp = pickUp;
        this.dropOff = dropOff;
        this.totalRateNett = totalRateNett;
        this.totalRateGross = totalRateGross;
        this.totalTaxPercent = totalTaxPercent;
        this.createDateTime = createDateTime;
    }

    public RentalDetails(Long vehicleId, Long customerId, Long employeeId, LocalDate pickUp, LocalDate dropOff, BigDecimal totalRateNett, BigDecimal totalRateGross, BigDecimal totalTaxPercent, LocalDate createDateTime) {
        this.vehicleId = vehicleId;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.pickUp = pickUp;
        this.dropOff = dropOff;
        this.totalRateNett = totalRateNett;
        this.totalRateGross = totalRateGross;
        this.totalTaxPercent = totalTaxPercent;
        this.createDateTime = createDateTime;
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getPickUp() {
        return pickUp;
    }

    public void setPickUp(LocalDate pickUp) {
        this.pickUp = pickUp;
    }

    public LocalDate getDropOff() {
        return dropOff;
    }

    public void setDropOff(LocalDate dropOff) {
        this.dropOff = dropOff;
    }

    public BigDecimal getTotalRateNett() {
        return totalRateNett;
    }

    public void setTotalRateNett(BigDecimal totalRateNett) {
        this.totalRateNett = totalRateNett;
    }

    public BigDecimal getTotalRateGross() {
        return totalRateGross;
    }

    public void setTotalRateGross(BigDecimal totalRateGross) {
        this.totalRateGross = totalRateGross;
    }

    public BigDecimal getTotalTaxPercent() {
        return totalTaxPercent;
    }

    public void setTotalTaxPercent(BigDecimal totalTaxPercent) {
        this.totalTaxPercent = totalTaxPercent;
    }

    public LocalDate getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDate createDateTime) {
        this.createDateTime = createDateTime;
    }
}
