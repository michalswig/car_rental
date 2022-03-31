package com.company.service;

import com.company.dto.Customer;
import com.company.exception.DataBaseException;
import com.company.dao.CustomerDAO;

import java.util.List;

public class CustomerService {
    private final CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public void create(Customer customer) throws DataBaseException {
        customerDAO.create(new Customer(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPersonalIdNumber(),
                customer.getCity(),
                customer.getPostalCode(),
                customer.getStreet(),
                customer.getStreetNumber(),
                customer.getApartmentNumber(),
                customer.getCompanyName(),
                customer.getTaxIdNumber(),
                customer.getCompanyCity(),
                customer.getCompanyPostalCode(),
                customer.getCompanyStreet(),
                customer.getCompanyStreetNumber(),
                customer.getCompanyApartmentNumber()
        ));
    }

    public Customer get(Long id) {
        return customerDAO.get(id);
    }

    public List<Customer> getAll() {
        return customerDAO.getAll();
    }

    public List<Customer> find(Customer customer) {
        return customerDAO.find(customer);
    }

    public void update(Customer customer) {
        customerDAO.update(customer);
    }


}
