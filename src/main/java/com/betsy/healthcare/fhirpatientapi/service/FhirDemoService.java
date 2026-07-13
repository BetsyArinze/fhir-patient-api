package com.betsy.healthcare.fhirpatientapi.service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.stereotype.Service;

@Service
public class FhirDemoService {
    private final FhirContext fhirContext;

    public FhirDemoService(FhirContext fhirContext) {
        this.fhirContext = fhirContext;
    }

    public String encodePatient() {

        Patient patient = new Patient();

        patient.setId("123");

        patient.setActive(true);

        patient.addIdentifier()
                .setSystem("http://hospital.com/mrn")
                .setValue("MRN001");

        patient.addName()
                .setFamily("Doe")
                .addGiven("John");

        patient.setGender(
                org.hl7.fhir.r4.model.Enumerations.AdministrativeGender.MALE
        );

        IParser parser = fhirContext.newJsonParser();

        parser.setPrettyPrint(true);

        return parser.encodeResourceToString(patient);
    }


    public Patient parsePatient(String json){

        IParser parser = fhirContext.newJsonParser();

        return parser.parseResource(Patient.class, json);

    }

}
