package ma.m0hamed.tp6.security.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.m0hamed.tp6.security.entities.AppRole;
import ma.m0hamed.tp6.security.entities.AppUser;
import ma.m0hamed.tp6.security.repositories.AppRoleRepository;
import ma.m0hamed.tp6.security.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j  // lambok slf4j : logger des infos
@AllArgsConstructor
@Transactional // tout les methode seront transactionel, c'est spring qui gère les transactions // .springframework
public class SecurityServiceImpl implements SecurityService{
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if(!password.equals(rePassword)) throw new RuntimeException("Password not Match!!");
        String hashedPWD = passwordEncoder.encode(password);
        AppUser appUser = new AppUser();
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashedPWD);
        appUser.setActive(true);
        AppUser savedAppUser = appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {
        AppRole approle = appRoleRepository.findByRoleName(roleName);
        if(approle != null) throw new RuntimeException("Role"+roleName+"Already exist !!");
        approle = new AppRole();
        approle.setRoleName(roleName);
        approle.setDescription(description);
        AppRole savedAppRole = appRoleRepository.save(approle);
        return savedAppRole;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appuser = appUserRepository.findByUsername(username);
        if(appuser==null) throw new RuntimeException("User "+username+" Not Found");
        AppRole approle = appRoleRepository.findByRoleName(roleName);
        if(approle==null) throw new RuntimeException("Role "+roleName+" Not Found");
        appuser.getUserRoles().add(approle);
        appUserRepository.save(appuser); // pas necessaire puisque methode est transactionel
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
