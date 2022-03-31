package com.company.dto;

import java.math.BigDecimal;

public class TopEarnersByMonth {
    private Long id;
    private String registrationNumber;
    private String brand;
    private BigDecimal totalRevenue;
    private Long dropOffMonth;
    private Long getDropOffYear;

    public TopEarnersByMonth(Long id, String registrationNumber, String brand, BigDecimal totalRevenue, Long dropOffMonth, Long getDropOffYear) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.totalRevenue = totalRevenue;
        this.dropOffMonth = dropOffMonth;
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

    public Long getDropOffMonth() {
        return dropOffMonth;
    }

    public Long getGetDropOffYear() {
        return getDropOffYear;
    }
}
