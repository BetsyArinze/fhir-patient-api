package com.betsy.healthcare.fhirpatientapi.repository;

import com.betsy.healthcare.fhirpatientapi.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<PatientEntity, String> {
    List<PatientEntity> findByFirstNameIgnoreCase(String firstName);
}
