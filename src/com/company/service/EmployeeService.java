package com.company.service;

import com.company.dto.Employee;
import com.company.dao.EmployeeDAO;

import java.util.List;

public class EmployeeService {
    EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public Employee get(Long id) {
        return employeeDAO.get(id).get();
    }

    public List<Employee> getALl() {
        return employeeDAO.getAll();
    }

    public void create(Employee employee) {
        employeeDAO.create(new Employee(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getPersonalIdNumber(),
                employee.getCity(),
                employee.getPostalCode(),
                employee.getStreetName(),
                employee.getStreetNumber(),
                employee.getApartmentNumber()
        ));
    }

    public void update(Employee employee) {
        employeeDAO.update(employee);
    }

    public List<Employee> find(Employee employee) {
        return employeeDAO.find(employee);
    }

}
