package com.betsy.healthcare.fhirpatientapi.mapper;

import com.betsy.healthcare.fhirpatientapi.dto.PatientRequest;
import com.betsy.healthcare.fhirpatientapi.entity.PatientEntity;
import org.hl7.fhir.r4.model.Enumerations;
import org.hl7.fhir.r4.model.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public PatientEntity toEntity(PatientRequest request) {
        PatientEntity entity = new PatientEntity();

        entity.setIdentifier(request.getIdentifier());
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setGender(request.getGender());
        entity.setBirthDate(LocalDate.parse(request.getBirthDate()));
        entity.setActive(true);

        return entity;
    }

    public Patient toFhirPatient(PatientEntity entity) {
        Patient patient = new Patient();

        patient.setId(entity.getId());

        patient.addIdentifier()
                .setSystem("http://hospital.com/mrn")
                .setValue(entity.getIdentifier());

        patient.addName()
                .setFamily(entity.getLastName())
                .addGiven(entity.getFirstName());

        patient.setGender(
                Enumerations.AdministrativeGender.fromCode(entity.getGender())
        );

        patient.setBirthDate(java.sql.Date.valueOf(entity.getBirthDate()));

        patient.setActive(entity.isActive());

        return patient;
    }
}
