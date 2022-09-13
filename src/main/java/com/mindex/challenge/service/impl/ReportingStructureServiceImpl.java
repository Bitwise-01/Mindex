package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Locating the reporting structure of employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);
        ReportingStructure reportingStructure = new ReportingStructure();

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        reportingStructure.setEmployee(employee);

        int total = getReportCount(id);
        reportingStructure.setNumberOfReports(total);

        return reportingStructure;
    }

    private int getReportCount(String employeeId) {
        int total = 0;
        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        if (employee == null) {
            return total;
        }

        List<Employee> employeeReports = employee.getDirectReports();

        if (employeeReports == null) {
            return total;
        }

        total += employeeReports.size();

        for (Employee employee2 : employeeReports) {
            total += getReportCount(employee2.getEmployeeId());
        }

        return total;
    }
}
