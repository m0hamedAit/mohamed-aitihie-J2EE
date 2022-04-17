package com.example.activity1.security.service;

import com.example.activity1.security.entities.AppUser;

public interface SecurityService {
    void saveUser(String username, String password, String rePassword);
    void saveRole(String roleName, String description);
    void addRoleToUser(String username, String roleName);
    void removeRoleFromUser(String username, String roleName);
    AppUser loadUserByUserName(String username);
}
