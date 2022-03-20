package ma.enset.tp4.service;

import lombok.AllArgsConstructor;
import ma.enset.tp4.entities.Role;
import ma.enset.tp4.entities.User;
import ma.enset.tp4.repositories.RoleRepository;
import ma.enset.tp4.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor  //
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public User addUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = this.findUserByUsername(username);
        Role role = this.findRoleByRoleName(roleName);
        if(user.getRoles()!=null) {
            user.getRoles().add(role);
            role.getUsers().add(user);
        }

    }
}
