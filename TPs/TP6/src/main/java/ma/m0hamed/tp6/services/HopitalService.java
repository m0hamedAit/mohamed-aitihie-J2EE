package ma.m0hamed.tp6.services;


import ma.m0hamed.tp6.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HopitalService {

    Patient getPatient(Long patientId);
    Patient savePatient(Patient patient);
    void deletePatient(Long patientId);
    Page<Patient> getPatientByName(String keyword, Pageable pageable);
}

