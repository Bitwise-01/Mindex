package com.mindex.challenge.service;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.Compensation;

public interface CompensationService {
    Compensation create(Compensation compensation);

    Compensation read(Employee employee);
}
