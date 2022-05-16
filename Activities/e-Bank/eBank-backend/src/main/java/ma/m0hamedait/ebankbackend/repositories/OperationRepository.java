package ma.m0hamedait.ebankbackend.repositories;

import ma.m0hamedait.ebankbackend.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findByAccountId(String accountId);
    Page<Operation> findByAccountId(String accountId, Pageable pageable);
}
