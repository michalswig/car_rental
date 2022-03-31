package com.company.dto;

import java.time.LocalDate;

public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String personalIdNumber;
    private String city;
    private String postalCode;
    private String streetName;
    private Integer streetNumber;
    private Integer apartmentNumber;
    private LocalDate removeDateTime;


    public Employee(String firstName, String lastName, String personalIdNumber, String city, String postalCode, String streetName, Integer streetNumber, Integer apartmentNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalIdNumber = personalIdNumber;
        this.city = city;
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.apartmentNumber = apartmentNumber;
    }

    public Employee(Long id, String firstName, String lastName, String personalIdNumber, String city, String postalCode, String streetName, Integer streetNumber, Integer apartmentNumber, LocalDate removeDateTime) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalIdNumber = personalIdNumber;
        this.city = city;
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.apartmentNumber = apartmentNumber;
        this.removeDateTime = removeDateTime;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Integer getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(Integer apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonalIdNumber() {
        return personalIdNumber;
    }

    public void setPersonalIdNumber(String personalIdNumber) {
        this.personalIdNumber = personalIdNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public LocalDate getRemoveDateTime() {
        return removeDateTime;
    }

    public void setRemoveDateTime(LocalDate removeDateTime) {
        this.removeDateTime = removeDateTime;
    }
}
