package ma.m0hamed.tp6.security.service;

import ma.m0hamed.tp6.security.entities.AppRole;
import ma.m0hamed.tp6.security.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(String username, String password, String rePassword);
    AppRole saveNewRole(String roleName, String description);
    void addRoleToUser(String username, String roleName);
    void removeRoleFromUser(String username, String roleName);
    AppUser loadUserByUserName(String username);
}
