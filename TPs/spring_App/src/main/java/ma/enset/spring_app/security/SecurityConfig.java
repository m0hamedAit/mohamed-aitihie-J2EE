package ma.enset.spring_app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration //s'instancie en premier lieu pour appliquer les configs
@EnableWebSecurity  // appliquer web security
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception { //pour specifier les droits d'acces
        //http.formLogin().loginPage("/login"); // spring security a une page de login par defaut mais pour specifier une autre, on le fait de cette maniere
        http.formLogin(); //utiliser page login par defaut
        http.authorizeRequests().antMatchers("/").permitAll(); // anyone can access
        http.authorizeRequests().antMatchers("/delete/**", "/edit/**", "/save/**", "/new/**").hasRole("ADMIN"); //ces url ne sont accessible que pour l'admin
        //http.authorizeRequests().anyRequest().authenticated(); // n'import quelle requete http necessitera une authentification

        http.exceptionHandling().accessDeniedPage("/403");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();
        auth.inMemoryAuthentication()
                .withUser("user1").password("{noop}1234").roles("USER") // c'est on n'ajoute pas un pass encoder, l'authentification ne va pas marché, sauf si on l'indique par {noop} => ".password("{noop}1234")"
                .and()
                .withUser("user2").password(passwordEncoder.encode("1234")).roles("USER") //
                .and()
                .withUser("admin").password(passwordEncoder.encode("admin")).roles("USER","ADMIN");  //InMemoryAuthentification pratique test de l'app
    }

    @Bean //pour l'instancier au demarrage et si on veut l'utiliser dans une autre couche, il suffit d'ajouter @Autowired
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();  // a chaque fois on utilise un password encoder, c'est Bcrypt qui sera utiliser
    }
}


// pour le hashage des mdp, c'est mieux d'utiliser Bcrypt et pas md5(depassé)