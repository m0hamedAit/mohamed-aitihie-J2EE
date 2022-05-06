package ma.m0hamedait.ebankbackend.repositories;

import ma.m0hamedait.ebankbackend.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

}
