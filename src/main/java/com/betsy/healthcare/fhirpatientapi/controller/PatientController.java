package com.betsy.healthcare.fhirpatientapi.controller;

import com.betsy.healthcare.fhirpatientapi.dto.PatientRequest;
import com.betsy.healthcare.fhirpatientapi.service.PatientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Patient")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public String createPatient(@RequestBody PatientRequest request) {
        return patientService.createPatient(request);
    }

    @GetMapping("/{id}")
    public String getPatient(@PathVariable("id") String id) {
        return patientService.getPatient(id);
    }

    @GetMapping
    public String searchPatients(@RequestParam("name") String name) {
        return patientService.searchPatients(name);
    }
}
