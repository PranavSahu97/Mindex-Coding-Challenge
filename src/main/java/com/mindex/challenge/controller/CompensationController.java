package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompensationController {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compensationService;

    @RequestMapping(value = "/compensation", method = RequestMethod.POST)
    public ResponseEntity<Compensation> createCompensation(@RequestBody Compensation compensation) {
        LOG.debug("Received compensation create request for [{}]", compensation);

        return new ResponseEntity<>(compensationService.create(compensation), HttpStatus.OK);
    }

    @RequestMapping(value = "/compensation/{id}", method = RequestMethod.GET)
    public ResponseEntity<Compensation> getCompensation(@PathVariable String id) {
        LOG.debug("Received compensation get request for [{}]", id);

        return new ResponseEntity<>(compensationService.read(id), HttpStatus.OK);
    }
}
