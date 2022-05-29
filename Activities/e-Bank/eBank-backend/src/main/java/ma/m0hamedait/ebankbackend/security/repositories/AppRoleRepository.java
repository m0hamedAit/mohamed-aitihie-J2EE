package ma.m0hamedait.ebankbackend.security.repositories;

import ma.m0hamedait.ebankbackend.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByRolename(String rolename);
}
