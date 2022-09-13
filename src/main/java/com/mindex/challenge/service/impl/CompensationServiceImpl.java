package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;

import java.util.Date;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    @Override
    public Compensation read(Employee employee) {
        LOG.debug("Locating the reporting structure of employee with id [{}]", employee.getEmployeeId());

        Compensation compensation = compensationRepository.findByEmployee(employee);

        if (compensation == null) {
            throw new RuntimeException("Employee has no compensation associated with it");
        }

        return compensation;
    }

    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating the compensation for employee with id [{}]", compensation.getEmployee().getEmployeeId());

        if (compensationRepository.findByEmployee(compensation.getEmployee()) != null) {
            throw new RuntimeException("Employee already has compensation associated with it");
        }

        return compensation;
    }
}
