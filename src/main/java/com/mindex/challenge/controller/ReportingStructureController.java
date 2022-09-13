package com.mindex.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.mindex.challenge.data.ReportingStructure;
import org.springframework.beans.factory.annotation.Autowired;
import com.mindex.challenge.service.ReportingStructureService;

@RestController
public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    private ReportingStructureService reportingStructureService;

    @GetMapping("/reports/{id}")
    public ReportingStructure read(@PathVariable String id) {
        LOG.debug("Received report structure request for employee with id [{}]", id);

        return reportingStructureService.read(id);
    }
}
