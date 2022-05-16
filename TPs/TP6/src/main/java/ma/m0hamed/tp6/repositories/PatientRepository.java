package ma.m0hamed.tp6.repositories;

import ma.m0hamed.tp6.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("select p from Patient p where p.firstName like %:key% or p.lastName like %:key%")
    Page<Patient> findByNameContains(@Param("key")String keyword, Pageable pageable);
}

