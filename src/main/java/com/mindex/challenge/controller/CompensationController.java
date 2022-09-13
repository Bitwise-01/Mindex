package com.mindex.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.Compensation;
import org.springframework.web.bind.annotation.*;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class CompensationController {
    private final double MINI_WAGE = 13.20;
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compensationService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/compensation")
    public Compensation create(@RequestBody Compensation compensationInfo) {
        LOG.debug("Received request to create compensation for an employee");

        // Make sure we get the minimum requirements from user
        if (compensationInfo.getEmployee().getEmployeeId() == null) {
            throw new RuntimeException("employee.employeeId is a required field");
        }

        if (compensationInfo.getEffectiveDate() == null) {
            throw new RuntimeException("effectiveDate is a required field");
        }

        if (compensationInfo.getSalary() <= MINI_WAGE) {
            throw new RuntimeException("salary must be at least $" + MINI_WAGE);
        }

        Employee employee = employeeService.read(compensationInfo.getEmployee().getEmployeeId());
        Compensation compensation = new Compensation(
                employee,
                compensationInfo.getSalary(),
                compensationInfo.getEffectiveDate());

        return compensationService.create(compensation);
    }

    @GetMapping("/compensation/{id}")
    public Compensation read(@PathVariable String id) {
        LOG.debug("Received request to get compensation for employee with id [{}]", id);

        Employee employee = employeeService.read(id);
        return compensationService.read(employee);
    }
}
