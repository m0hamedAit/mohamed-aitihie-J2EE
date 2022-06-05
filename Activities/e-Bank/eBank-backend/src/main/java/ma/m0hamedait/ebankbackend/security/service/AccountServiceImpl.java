package ma.m0hamedait.ebankbackend.security.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.m0hamedait.ebankbackend.security.entities.AppRole;
import ma.m0hamedait.ebankbackend.security.entities.AppUser;
import ma.m0hamedait.ebankbackend.security.repositories.AppRoleRepository;
import ma.m0hamedait.ebankbackend.security.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser addUser(AppUser appUser) {
        String pw = appUser.getPassword();
        System.out.println(appUser.getUsername());
        appUser.setPassword(passwordEncoder.encode(pw));
        log.info("{} was added !", appUser.getUsername());
        return appUserRepository.save(appUser);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> usersList() {
        return appUserRepository.findAll();
    }

    @Override
    public AppRole addRole(String roleName) {
        AppRole appRole = new AppRole();
        appRole.setRolename(roleName);
        log.info("Role {} was added !", appRole.getRolename());
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findAppRoleByRolename(rolename);
        appUser.getAppRoles().add(appRole);

        log.info("role {} was added to {} !", appRole.getRolename(), appUser.getUsername());
    }
}
