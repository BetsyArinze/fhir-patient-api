package com.betsy.healthcare.fhirpatientapi.controller;

import com.betsy.healthcare.fhirpatientapi.service.FhirDemoService;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/demo")
public class FhirDemoController {
    private final FhirDemoService fhirDemoService;

    public FhirDemoController(FhirDemoService fhirDemoService) {
        this.fhirDemoService = fhirDemoService;
    }

    @GetMapping("/encode")
    public String encodePatient() {

        return fhirDemoService.encodePatient();

    }

    @PostMapping("/parse")
    public String parsePatient(@RequestBody String json){

        Patient patient = fhirDemoService.parsePatient(json);

        return patient.getNameFirstRep().getFamily();

    }
}
