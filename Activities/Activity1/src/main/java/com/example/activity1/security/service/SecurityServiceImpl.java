package com.example.activity1.security.service;

import com.example.activity1.security.entities.AppRole;
import com.example.activity1.security.entities.AppUser;
import com.example.activity1.security.repositories.AppRoleRepository;
import com.example.activity1.security.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SecurityServiceImpl implements SecurityService{
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(String username, String password, String rePassword) {
        if(!password.equals(rePassword)) throw new RuntimeException("Password not Match!!");
        String hashedPWD = passwordEncoder.encode(password);
        AppUser appUser = new AppUser();
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashedPWD);
        appUser.setActive(true);
        appUserRepository.save(appUser);
    }

    @Override
    public void saveRole(String roleName, String description) {
        AppRole approle = appRoleRepository.findByRoleName(roleName);
        if(approle != null) throw new RuntimeException("Role"+roleName+"Already exist !!");
        approle = new AppRole();
        approle.setRoleName(roleName);
        approle.setDescription(description);
        appRoleRepository.save(approle);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appuser = appUserRepository.findByUsername(username);
        if(appuser==null) throw new RuntimeException("User "+username+" Not Found");
        AppRole approle = appRoleRepository.findByRoleName(roleName);
        if(approle==null) throw new RuntimeException("Role "+roleName+" Not Found");
        appuser.getUserRoles().add(approle);
        appUserRepository.save(appuser);
    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        AppUser appuser = appUserRepository.findByUsername(username);
        if(appuser==null) throw new RuntimeException("User "+username+" Not Found");
        AppRole approle = appRoleRepository.findByRoleName(roleName);
        if(approle==null) throw new RuntimeException("Role "+roleName+" Not Found");
        appuser.getUserRoles().remove(approle);
    }

    @Override
    public AppUser loadUserByUserName(String username) {
        return appUserRepository.findByUsername(username);
    }
}


