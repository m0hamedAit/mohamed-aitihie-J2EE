package ma.m0hamedait.ebankbackend.repositories;

import ma.m0hamedait.ebankbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByName(String name);
    Optional<Customer> findCustomerByEmail(String email);
}
