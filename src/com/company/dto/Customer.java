package com.company.dto;

import java.time.LocalDate;

public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String personalIdNumber;
    private String city;
    private String postalCode;
    private String street;
    private Integer streetNumber;
    private Integer apartmentNumber;
    private String companyName;
    private String taxIdNumber;
    private String companyCity;
    private String companyPostalCode;
    private String companyStreet;
    private Integer companyStreetNumber;
    private Integer companyApartmentNumber;
    private LocalDate removeDateTime;

    public Customer(String firstName, String lastName, String personalIdNumber, String city, String postalCode, String street, Integer streetNumber, Integer apartmentNumber, String companyName, String taxIdNumber, String companyCity, String companyPostalCode, String companyStreet, Integer companyStreetNumber, Integer companyApartmentNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalIdNumber = personalIdNumber;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.streetNumber = streetNumber;
        this.apartmentNumber = apartmentNumber;
        this.companyName = companyName;
        this.taxIdNumber = taxIdNumber;
        this.companyCity = companyCity;
        this.companyPostalCode = companyPostalCode;
        this.companyStreet = companyStreet;
        this.companyStreetNumber = companyStreetNumber;
        this.companyApartmentNumber = companyApartmentNumber;
    }

    public Customer(Long id, String firstName, String lastName, String personalIdNumber, String city, String postalCode, String street, Integer streetNumber, Integer apartmentNumber, String companyName, String taxIdNumber, String companyCity, String companyPostalCode, String companyStreet, Integer companyStreetNumber, Integer companyApartmentNumber, LocalDate removeDateTime) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalIdNumber = personalIdNumber;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.streetNumber = streetNumber;
        this.apartmentNumber = apartmentNumber;
        this.companyName = companyName;
        this.taxIdNumber = taxIdNumber;
        this.companyCity = companyCity;
        this.companyPostalCode = companyPostalCode;
        this.companyStreet = companyStreet;
        this.companyStreetNumber = companyStreetNumber;
        this.companyApartmentNumber = companyApartmentNumber;
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

    public Integer getCompanyStreetNumber() {
        return companyStreetNumber;
    }

    public void setCompanyStreetNumber(Integer companyStreetNumber) {
        this.companyStreetNumber = companyStreetNumber;
    }

    public Integer getCompanyApartmentNumber() {
        return companyApartmentNumber;
    }

    public void setCompanyApartmentNumber(Integer companyApartmentNumber) {
        this.companyApartmentNumber = companyApartmentNumber;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTaxIdNumber() {
        return taxIdNumber;
    }

    public void setTaxIdNumber(String taxIdNumber) {
        this.taxIdNumber = taxIdNumber;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyPostalCode() {
        return companyPostalCode;
    }

    public void setCompanyPostalCode(String companyPostalCode) {
        this.companyPostalCode = companyPostalCode;
    }

    public String getCompanyStreet() {
        return companyStreet;
    }

    public void setCompanyStreet(String companyStreet) {
        this.companyStreet = companyStreet;
    }

    public LocalDate getRemoveDateTime() {
        return removeDateTime;
    }

    public void setRemoveDateTime(LocalDate removeDateTime) {
        this.removeDateTime = removeDateTime;
    }

}
