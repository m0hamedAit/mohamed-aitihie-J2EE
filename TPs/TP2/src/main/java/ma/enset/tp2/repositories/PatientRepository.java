package ma.enset.tp2.repositories;

import ma.enset.tp2.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByMalade(boolean m);
    Page<Patient> findByMalade(boolean m, Pageable pageable);  // avec pagination
    List<Patient> findByMaladeAndScoreLessThan(boolean m, int score); // p.malade = m, p.score <score
    List<Patient> findByMaladeIsTrueAndScoreLessThan( int score); // p.malade = true, p.score <score
    List<Patient> findByDateNaissanceBetween(Date d1, Date d2); // d1 < p.dateNaissance < d2

    List<Patient> findByDateNaissanceBetweenAndMaladeIsTrueOrNomLike(Date d1, Date d2, String mc); // d1 < p.dateNaissance < d2 and (p.malade = true or p.nom like mc)
    //Or
    @Query("select p from Patient p where p.dateNaissance between :x and :y or p.nom like :z")
    List<Patient> searchPatients(
            @Param("x") Date d1,
            @Param("y") Date d2,
            @Param("z") String mc
    );
}
