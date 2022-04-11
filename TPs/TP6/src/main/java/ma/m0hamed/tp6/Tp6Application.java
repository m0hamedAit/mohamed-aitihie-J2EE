package ma.m0hamed.tp6;

import ma.m0hamed.tp6.entities.Patient;
import ma.m0hamed.tp6.repositories.PatientRepository;
import ma.m0hamed.tp6.security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class Tp6Application {

    public static void main(String[] args) {
        SpringApplication.run(Tp6Application.class, args);
    }

    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
        return args -> {
            patientRepository.save(new Patient(null, "hassan", "Ajkh", "hassan@email.com" , new Date(), false));
            patientRepository.save(new Patient(null, "Mohamed", "Bqsd", "mohamed@email.com" , new Date(), true));
            patientRepository.save(new Patient(null, "Yassmin", "Csdfs", "yassmine@email.com" , new Date(), false));
            patientRepository.save(new Patient(null, "Hanae", "Dqsdf", "hanae@email.com" ,  new Date(), true));

            patientRepository.findAll().forEach(p->{
                System.out.println(p.getFirstName());
            });
        };
    }

    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService) {
        return args -> {
            securityService.saveNewUser("mohamed", "1234", "1234");
            securityService.saveNewUser("Anas", "12345", "12345");

            securityService.saveNewRole("USER", "");
            securityService.saveNewRole("ADMIN", "");

            securityService.addRoleToUser("mohamed", "USER");
            securityService.addRoleToUser("mohamed", "ADMIN");
            securityService.addRoleToUser("Anas", "USER");
        };
    }

    @Bean //pour l'instancier au demarrage et si on veut l'utiliser dans une autre couche, il suffit d'ajouter @Autowired
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();  // a chaque fois on utilise un password encoder, c'est Bcrypt qui sera utiliser
    }
}