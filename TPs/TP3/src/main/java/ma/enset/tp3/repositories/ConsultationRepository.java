package ma.enset.tp3.repositories;

import ma.enset.tp3.entities.Consultation;
import ma.enset.tp3.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}

