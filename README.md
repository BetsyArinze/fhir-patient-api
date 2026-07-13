# FHIR Patient API

A Spring Boot application that demonstrates healthcare interoperability through the implementation of **FHIR-compliant
Patient resources** using the **HAPI FHIR** library. The project follows a layered architecture, stores patient
information in **PostgreSQL**, and exposes FHIR-compliant REST endpoints for standardized exchange of healthcare data
between systems.

---

## Features

* Create a FHIR Patient resource
* Retrieve a patient by ID
* Search patients by first name using FHIR-style query parameters
* Store patient data in PostgreSQL
* Convert database entities into FHIR-compliant Patient resources
* Serialize and deserialize FHIR JSON using HAPI FHIR
* Clean layered architecture (Controller → Service → Repository → Mapper)

---

## Tech Stack

* Java 21
* Spring Boot
* Spring Data JPA
* PostgreSQL
* HAPI FHIR (R4)
* Maven

---

## API Endpoints

| Method | Endpoint               | Description                        |
|--------|------------------------|------------------------------------|
| POST   | `/Patient`             | Create a new FHIR Patient resource |
| GET    | `/Patient/{id}`        | Retrieve a patient by ID           |
| GET    | `/Patient?name={name}` | Search patients by name            |
