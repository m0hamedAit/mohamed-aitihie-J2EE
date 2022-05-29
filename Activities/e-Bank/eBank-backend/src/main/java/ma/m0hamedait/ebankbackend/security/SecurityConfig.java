package ma.m0hamedait.ebankbackend.security;

import lombok.AllArgsConstructor;
import ma.m0hamedait.ebankbackend.security.entities.AppUser;
import ma.m0hamedait.ebankbackend.security.filters.JwtAuthenticationFilter;
import ma.m0hamedait.ebankbackend.security.filters.JwtAuthorizationFilter;
import ma.m0hamedait.ebankbackend.security.service.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private AccountService accountService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {  // qu'on le user saisi son username et pwd, on appele la methode loadUserByUsername()
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                AppUser appUser = accountService.loadUserByUsername(username);
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                appUser.getAppRoles().forEach(r->{
                    authorities.add(new SimpleGrantedAuthority(r.getRolename()));
                });
                return new User(appUser.getUsername(), appUser.getPassword(), authorities);
            }
        });
    } // au lieu de memory ou jdbc authentication, c'est mieux d'utiliser userDetailsService auth pour indiquer a l'app ou chercher le user(c'est plus generale)

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); // disable csrf
        http.headers().frameOptions().disable();  //h2 utilise des frames, or spring security les block pour raison de securité, on enleve se blockage
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // on switch vers une authentification stateless (on utilisant des tokens au lieu de session et cookies)
        //http.authorizeRequests().antMatchers(HttpMethod.POST, "/users/**").hasAuthority("ADMIN");
        //http.authorizeRequests().antMatchers(HttpMethod.GET, "/users/**").hasAuthority("USER");
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
        http.authorizeRequests().anyRequest().permitAll();  // acces sans password de spring security
        //http.formLogin(); // qu'on un utilisateur veut acceder a une ressource dont il n'a pas le droit, on lui affiche le login page
        //http.authorizeRequests().anyRequest().authenticated();  // l'utilisateur doit etre authentifier pour n'import quelle request

        http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        //**http.authorizeRequests().anyRequest().authenticated();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
