package com.company.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Vehicle {
    private Long id;
    private String registrationNumber;
    private String brand;
    private String model;
    private String vinNumber;
    private BigDecimal currentRateNett;
    private BigDecimal currentRateGross;
    private BigDecimal currentTaxPercent;
    private LocalDate removeDateTime;

    public Vehicle(Long id, String registrationNumber, String brand, String model, String vinNumber, BigDecimal currentRateNett, BigDecimal currentRateGross, BigDecimal taxPercent, LocalDate removeDateTime) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.model = model;
        this.vinNumber = vinNumber;
        this.currentRateNett = currentRateNett;
        this.currentRateGross = currentRateGross;
        this.currentTaxPercent = taxPercent;
        this.removeDateTime = removeDateTime;
    }

    public Vehicle(String registrationNumber, String brand, String model, String vinNumber, BigDecimal currentRateNett, BigDecimal currentRateGross, BigDecimal currentTaxPercent) {
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.model = model;
        this.vinNumber = vinNumber;
        this.currentRateNett = currentRateNett;
        this.currentRateGross = currentRateGross;
        this.currentTaxPercent = currentTaxPercent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public BigDecimal getCurrentRateNett() {
        return currentRateNett;
    }

    public void setCurrentRateNett(BigDecimal currentRateNett) {
        this.currentRateNett = currentRateNett;
    }

    public BigDecimal getCurrentRateGross() {
        return currentRateGross;
    }

    public void setCurrentRateGross(BigDecimal currentRateGross) {
        this.currentRateGross = currentRateGross;
    }

    public BigDecimal getCurrentTaxPercent() {
        return currentTaxPercent;
    }

    public void setCurrentTaxPercent(BigDecimal currentTaxPercent) {
        this.currentTaxPercent = currentTaxPercent;
    }

    public LocalDate getRemoveDateTime() {
        return removeDateTime;
    }

    public void setRemoveDateTime(LocalDate removeDateTime) {
        this.removeDateTime = removeDateTime;
    }
}
