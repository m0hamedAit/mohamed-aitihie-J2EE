package ma.m0hamed.tp6.security;

import ma.m0hamed.tp6.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration //s'instancie en premier lieu pour appliquer les configs
@EnableWebSecurity  // appliquer web security
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource; // from javax.sql.DataSource
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    protected void configure(HttpSecurity http) throws Exception { //pour specifier les droits d'acces
        //http.formLogin().loginPage("/login"); // spring security a une page de login par defaut mais pour specifier une autre, on le fait de cette maniere
        http.formLogin(); //utiliser page login par defaut
        http.authorizeRequests().antMatchers("/").permitAll(); // anyone can access
        //http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN"); //ces urls ne sont accessible que pour l'admin
        http.authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN"); // pour 'auth.userDetailsService', on utilise *hasAuthority* au lieu de *hasRole*
        http.authorizeRequests().antMatchers("/webjars/**").permitAll();  // autoriser acces aux ressources statics
        http.authorizeRequests().anyRequest().authenticated(); // n'import quelle autre requete http necessitera une authentification

        http.exceptionHandling().accessDeniedPage("/403");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // MemoryAuth
        /*auth.inMemoryAuthentication()
                .withUser("user1").password("{noop}1234").roles("USER") // c'est on n'ajoute pas un pass encoder, l'authentification ne va pas marché, sauf si on l'indique par {noop} => ".password("{noop}1234")"
                .and()
                .withUser("user2").password(passwordEncoder.encode("1234")).roles("USER") //
                .and()
                .withUser("admin").password(passwordEncoder.encode("admin")).roles("USER","ADMIN");  //InMemoryAuthentification pratique test de l'app
        */
        // jdbcAuth
        /*auth.jdbcAuthentication() // utiliser apres lors de l'authentification (saisie de login & pass)
                .dataSource(dataSource)   // datasource defini dans le fichier de configuration : notre base de données
                .usersByUsernameQuery("select username as principal, password as credentials, active from users where username=?") // chercher les users dans bd  // "as principal" est ajouter pour indiquer a Spring security que c'est la colonne username (dans Spring security, username=principal), de meme pour "as credentials"
                .authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username=?") // charger utilisateur et comparer le mdp saisi avec celui dans la bd
                .rolePrefix("ROLE_") // ajouter un prfix aux role, par exemple : pour "ADMIN" -> "ROLE_ADMIN"
                .passwordEncoder(passwordEncoder); // indiquer que les passwords sont encoder
*/
        //
        auth.userDetailsService(userDetailsService); // faire appel a cette methode, apres saisie de username et password

    }



}


// pour le hashage des mdp, c'est mieux d'utiliser Bcrypt et pas md5(depassé)