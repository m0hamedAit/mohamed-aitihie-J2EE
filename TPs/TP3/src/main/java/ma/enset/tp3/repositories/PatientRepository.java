package ma.enset.tp3.repositories;

import ma.enset.tp3.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {
    Patient findByNom(String nom);
}
