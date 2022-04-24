package com.example.activity1.security;

import com.example.activity1.security.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private DataSource dataSource;
    private UserDetailsServiceImpl userDetailsService;
    private PasswordEncoder passwordEncoder;

    public SecurityConfig(DataSource dataSource, UserDetailsServiceImpl userDetailsService,
                          PasswordEncoder passwordEncoder) {
        this.dataSource = dataSource;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home").authenticated()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll();
        http.csrf().disable();

        http.exceptionHandling().accessDeniedPage("/403");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //Web resources
        web.ignoring().antMatchers("/assets/**");
    }


}


// pour le hashage des mdp, c'est mieux d'utiliser Bcrypt et pas md5(depassé)