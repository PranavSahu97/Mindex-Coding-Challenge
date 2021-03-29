package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeService employeeService;

    @Override
    public ReportingStructure getReports(String id) {
        LOG.debug("Creating reportingStructure with id [{}]", id);

        Employee employee = employeeService.read(id);
        if(employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports(getDistinctReports(employee));

        return reportingStructure;
    }

    @Override
    public int getDistinctReports(Employee employee) {
        int reportCount = 0;
        Set<Employee> employeeSet = new HashSet<>();
        if(employee.getDirectReports() != null) {
            for(Employee e : employee.getDirectReports()){
                employeeSet.add(employeeService.read(e.getEmployeeId()));
            }

            reportCount = employeeSet.size();
        }

        return reportCount;
    }
}
