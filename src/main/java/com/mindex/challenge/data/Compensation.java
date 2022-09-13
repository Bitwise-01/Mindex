package com.mindex.challenge.data;

import java.util.Date;

public class Compensation {
    private Employee employee;
    private double salary;
    private Date effectiveDate;

    public Compensation() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getEmployeeId() {
        return employee.getEmployeeId();
    }

    public double getSalary() {
        return salary;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
