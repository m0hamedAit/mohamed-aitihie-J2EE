package ma.m0hamedait.ebankbackend.repositories;

import ma.m0hamedait.ebankbackend.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
