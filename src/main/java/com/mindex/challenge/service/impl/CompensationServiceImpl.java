package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
// import com.mindex.challenge.data.ReportingStructure;
// import com.mindex.challenge.service.ReportingStructureService;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import java.util.List;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    private CompensationRepository compensationRepository;

    @Override
    public Compensation read(String id) {
        LOG.debug("Locating the reporting structure of employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);
        Compensation compensation = compensationRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        if (compensation == null) {
            throw new RuntimeException("Employee has no compensation associated with it");
        }

        // reportingStructure.setEmployee(employee);

        // int total = getReportCount(id);
        // reportingStructure.setNumberOfReports(total);

        // return reportingStructure;

        return compensation;
    }

    // private int getReportCount(String employeeId) {
    // int total = 0;
    // Employee employee = employeeRepository.findByEmployeeId(employeeId);
    // if (employee == null) {
    // return total;
    // }

    // List<Employee> employeeReports = employee.getDirectReports();

    // if (employeeReports == null) {
    // return total;
    // }

    // total += employeeReports.size();

    // for (Employee employee2 : employeeReports) {
    // total += getReportCount(employee2.getEmployeeId());
    // }

    // return total;
    // }
}
