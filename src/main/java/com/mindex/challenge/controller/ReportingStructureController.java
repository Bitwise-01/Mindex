package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    private ReportingStructureService reportingStructureService;

    // @PostMapping("/employee")
    // public Employee create(@RequestBody Employee employee) {
    // LOG.debug("Received employee create request for [{}]", employee);

    // return employeeService.create(employee);
    // }

    @GetMapping("/reports/{id}")
    public ReportingStructure read(@PathVariable String id) {
        LOG.debug("Received employee get request for id [{}]", id);

        return reportingStructureService.read(id);
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
