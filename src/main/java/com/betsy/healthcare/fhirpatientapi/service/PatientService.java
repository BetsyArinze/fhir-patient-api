package com.betsy.healthcare.fhirpatientapi.service;

import ca.uhn.fhir.context.FhirContext;
import com.betsy.healthcare.fhirpatientapi.dto.PatientRequest;
import com.betsy.healthcare.fhirpatientapi.entity.PatientEntity;
import com.betsy.healthcare.fhirpatientapi.mapper.PatientMapper;
import com.betsy.healthcare.fhirpatientapi.repository.PatientRepository;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final FhirContext fhirContext;
    private final PatientMapper patientMapper;

    public PatientService(PatientRepository patientRepository, FhirContext fhirContext, PatientMapper patientMapper) {
        this.fhirContext = fhirContext;
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public String createPatient(PatientRequest request) {
        PatientEntity entity = patientMapper.toEntity(request);

        entity.setId(UUID.randomUUID().toString());

        patientRepository.save(entity);

        Patient patient = patientMapper.toFhirPatient(entity);

        return fhirContext.newJsonParser().setPrettyPrint(true).encodeResourceToString(patient);
    }

    public String getPatient(String id) {

        PatientEntity entity = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));


        Patient patient = patientMapper.toFhirPatient(entity);

        return fhirContext
                .newJsonParser()
                .setPrettyPrint(true)
                .encodeResourceToString(patient);
    }

    public String searchPatients(String name) {

        List<PatientEntity> entities = patientRepository.findByFirstNameIgnoreCase(name);

        Bundle bundle = new Bundle();
        bundle.setType(Bundle.BundleType.SEARCHSET);
        bundle.setTotal(entities.size());

        for (PatientEntity entity : entities) {
            Patient patient = patientMapper.toFhirPatient(entity);

            bundle.addEntry().setResource(patient);
        }

        return fhirContext.newJsonParser().setPrettyPrint(true).encodeResourceToString(bundle);
    }
}
