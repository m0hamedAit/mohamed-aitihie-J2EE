package ma.m0hamed.tp6.security.repositories;

import ma.m0hamed.tp6.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);
}
