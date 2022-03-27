package ma.enset.tp4.service;

import ma.enset.tp4.entities.Role;
import ma.enset.tp4.entities.User;

public interface UserService {
    User addUser(User user);
    Role addRole(Role role);

    User findUserByUsername(String username);
    Role findRoleByRoleName(String roleName);

    void addRoleToUser(String username, String roleName);

    User authenticate(String userName, String password);
}

