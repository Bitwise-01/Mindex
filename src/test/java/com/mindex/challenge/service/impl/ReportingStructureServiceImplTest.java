package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.data.ReportingStructure;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private String reportingstructureIdUrl;

    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        reportingstructureIdUrl = "http://localhost:" + port + "/reports/{id}";
    }

    @Test
    public void testRead() {
        ReportingStructure testReportingStructure = new ReportingStructure();

        // Create employee and report employees
        Employee employee = new Employee(
                "John", "Doe", "Accounting", "Manager");

        Employee employee1 = new Employee(
                "Jane", "Doe", "Accounting", "Lead manager");

        Employee employee2 = new Employee(
                "Jake", "State", "Customer Service", "customer service");

        // Combine and set things
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(employee1);
        employees.add(employee2);

        employee.setEmployeeId("12a596ae-edd3-4847-99fe-c4518e82c86a");
        employee.setDirectReports(employees);
        employeeService.create(employee);

        testReportingStructure.setEmployee(employee);
        testReportingStructure.setNumberOfReports(employees.size());

        // Read checks
        ReportingStructure readReportingStructure = restTemplate
                .getForEntity(reportingstructureIdUrl, ReportingStructure.class,
                        employee.getEmployeeId()

                ).getBody();

        assertNotNull(readReportingStructure);
        assertReportingStructureEquivalence(testReportingStructure,
                readReportingStructure);
    }

    private static void assertReportingStructureEquivalence(ReportingStructure expected, ReportingStructure actual) {
        assertEquals(expected.getEmployee().getEmployeeId(), actual.getEmployee().getEmployeeId());
        assertEquals(expected.getNumberOfReports(), actual.getNumberOfReports());
    }
}