package com.company.dto;

public class TopEarnersByRentedDays {
    private Long id;
    private String brand;
    private Integer rentedDays;
    private Integer notRentedDays;
    private Integer totalRev;
    private Integer revPerRentedDay;
    private Integer rentalsNumber;
    private Long year;

    public TopEarnersByRentedDays(Long id, String brand, Integer rentedDays, Integer notRentedDays, Integer totalRev, Integer revPerRentedDay, Integer rentalsNumber, Long year) {
        this.id = id;
        this.brand = brand;
        this.rentedDays = rentedDays;
        this.notRentedDays = notRentedDays;
        this.totalRev = totalRev;
        this.revPerRentedDay = revPerRentedDay;
        this.rentalsNumber = rentalsNumber;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public Integer getRentedDays() {
        return rentedDays;
    }

    public Integer getNotRentedDays() {
        return notRentedDays;
    }

    public Integer getTotalRev() {
        return totalRev;
    }

    public Integer getRevPerRentedDay() {
        return revPerRentedDay;
    }

    public Integer getRentalsNumber() {
        return rentalsNumber;
    }

    public Long getYear() {
        return year;
    }
}
