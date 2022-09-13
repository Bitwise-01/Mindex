package com.mindex.challenge.controller;

import java.util.Date;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compensationService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/compensation")
    public Compensation create(@RequestBody Compensation compensationInfo) {
        LOG.debug("Received employee create request for [{}]", compensationInfo.getEmployee().getEmployeeId());

        if (compensationInfo.getEmployee().getEmployeeId() == null) {
            throw new RuntimeException("employee.employeeId is a required field");
        }

        if (compensationInfo.getEffectiveDate() == null) {
            throw new RuntimeException("effectiveDate is a required field");
        }

        final double MINI_WAGE = 13.20;

        if (compensationInfo.getSalary() <= MINI_WAGE) {
            throw new RuntimeException("salary must be at least $" + MINI_WAGE);
        }

        Employee employee = employeeService.read(compensationInfo.getEmployee().getEmployeeId());

        Compensation compensation = new Compensation(
                employee,
                compensationInfo.getSalary(),
                compensationInfo.getEffectiveDate());

        System.out.println(compensation);

        return compensationService.create(compensation);
    }

    @GetMapping("/compensation/{id}")
    public Compensation read(@PathVariable String id) {
        LOG.debug("Received employee get request for id [{}]", id);
        Employee employee = employeeService.read(id);

        return compensationService.read(employee);
    }

    // @PutMapping("/employee/{id}")
    // public Employee update(@PathVariable String id, @RequestBody Employee
    // employee) {
    // LOG.debug("Received employee create request for id [{}] and employee [{}]",
    // id, employee);

    // employee.setEmployeeId(id);
    // return employeeService.update(employee);
    // }
}
