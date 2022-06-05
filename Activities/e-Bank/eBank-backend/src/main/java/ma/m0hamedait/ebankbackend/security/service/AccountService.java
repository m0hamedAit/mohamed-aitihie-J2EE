package ma.m0hamedait.ebankbackend.security.service;

import ma.m0hamedait.ebankbackend.security.entities.AppRole;
import ma.m0hamedait.ebankbackend.security.entities.AppUser;

import java.util.List;

public interface AccountService {
    //user
    List<AppUser> findAll();
    AppUser addUser(AppUser appUser);
    AppUser loadUserByUsername(String username);
    List<AppUser> usersList();
    //role
    AppRole addRole(String roleName);
    void addRoleToUser(String username, String rolename);
}
