package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.impl.ReportingStructureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportingStructureController {

    @Autowired
    private ReportingStructureServiceImpl reportingStructureService;

    @RequestMapping(value = "/reportingStructure/{employeeId}", method = RequestMethod.GET)
    public ResponseEntity<ReportingStructure> getReportingStructure(@PathVariable String employeeId) {
        if(reportingStructureService.getReports(employeeId) != null) {
            return new ResponseEntity<>(reportingStructureService.getReports(employeeId), HttpStatus.OK);
        } else
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
