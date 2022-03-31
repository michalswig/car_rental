package com.company.dto;

import java.math.BigDecimal;

public class TopEarnersByYear {
    private Long id;
    private String registrationNumber;
    private String brand;
    private BigDecimal totalRevenue;
    private Long getDropOffYear;

    public TopEarnersByYear(Long id, String registrationNumber, String brand, BigDecimal totalRevenue, Long getDropOffYear) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.totalRevenue = totalRevenue;
        this.getDropOffYear = getDropOffYear;
    }

    public Long getId() {
        return id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getBrand() {
        return brand;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public Long getGetDropOffYear() {
        return getDropOffYear;
    }
}
