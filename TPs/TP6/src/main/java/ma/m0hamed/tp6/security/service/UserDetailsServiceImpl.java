package ma.m0hamed.tp6.security.service;

import ma.m0hamed.tp6.security.entities.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SecurityService securityService;

    public UserDetailsServiceImpl(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = securityService.loadUserByUserName(username);

        Collection<GrantedAuthority> authorities1 =
                appUser.getUserRoles()
                        .stream()
                        .map(role-> new SimpleGrantedAuthority(role.getRoleName()))
                        .collect(Collectors.toList());

        User user = new User(appUser.getUsername(), appUser.getPassword(), authorities1);
        return user;
    }
}

