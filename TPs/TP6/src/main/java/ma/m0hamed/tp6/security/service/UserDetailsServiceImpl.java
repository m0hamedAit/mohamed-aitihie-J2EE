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

    private SecurityService securityService;

    public UserDetailsServiceImpl(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    //une fois l'utilisateur entre son login et pass, cette metode sera appeler
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = securityService.loadUserByUserName(username);

        // en programmation imperative
        /* Collection<GrantedAuthority> authorities = new ArrayList(); // pour spring security les roles doivent etre de type GrantedAuthority
        appUser.getUserRoles().forEach(role->{
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
            authorities.add(authority);
        });*/

        // en maniere declarative
        Collection<GrantedAuthority> authorities1 =
                appUser.getUserRoles()
                        .stream()
                        .map(role-> new SimpleGrantedAuthority(role.getRoleName()))
                        .collect(Collectors.toList());

        User user = new User(appUser.getUsername(), appUser.getPassword(), authorities1);  // c'est la class que va utiliser spring security et non pas celle qu'on a creer
        return user;
    }
}
