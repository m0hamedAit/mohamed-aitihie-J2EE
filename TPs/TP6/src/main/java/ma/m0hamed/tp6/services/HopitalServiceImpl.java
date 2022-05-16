package ma.m0hamed.tp6.services;

import lombok.AllArgsConstructor;
import ma.m0hamed.tp6.entities.Patient;
import ma.m0hamed.tp6.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class HopitalServiceImpl implements HopitalService {
    PatientRepository patientRepository;

    @Override
    public Patient getPatient(Long patientId) {
        return patientRepository.findById(patientId).orElse(null);
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(Long patientId) {
        patientRepository.deleteById(patientId);
    }

    @Override
    public Page<Patient> getPatientByName(String keyword, Pageable pageable) {
        return patientRepository.findByNameContains(keyword, pageable);
    }
}
