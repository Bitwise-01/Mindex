package com.mindex.challenge.controller;

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

    // @PostMapping("/employee")
    // public Employee create(@RequestBody Employee employee) {
    // LOG.debug("Received employee create request for [{}]", employee);

    // return employeeService.create(employee);
    // }

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
