package ma.enset.tp3.repositories;

import ma.enset.tp3.entities.Medecin;
import ma.enset.tp3.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
}
