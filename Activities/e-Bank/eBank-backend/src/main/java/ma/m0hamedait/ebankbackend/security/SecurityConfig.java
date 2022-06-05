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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private AccountService accountService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
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
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); // disable csrf
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.formLogin().disable().cors(httpSecurityCorsConfigurer -> {
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
            corsConfiguration.addAllowedMethod("DELETE");
            corsConfiguration.addAllowedMethod("PUT");
            corsConfiguration.addAllowedMethod("POST");
            corsConfiguration.addAllowedMethod("GET");
            source.registerCorsConfiguration("/**",  corsConfiguration);
            httpSecurityCorsConfigurer.configurationSource(source);
        });
        //http.authorizeRequests().antMatchers(HttpMethod.POST, "/users/**").hasAuthority("ADMIN");
        //http.authorizeRequests().antMatchers(HttpMethod.GET, "/users/**").hasAuthority("USER");
        http.authorizeHttpRequests().antMatchers("/login/**",
                "/swagger-ui**",
                "/swagger-ui/**",
                "/v3/**",
                "/api/v1/refresh-token").permitAll();
        http.authorizeRequests().anyRequest().permitAll();  // acces sans password de spring security
        //http.authorizeRequests().anyRequest().authenticated();

        http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        // http.authorizeRequests().anyRequest().authenticated();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
