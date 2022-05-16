package ma.m0hamed.tp6;

import com.github.javafaker.Faker;
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
        Faker faker = new Faker();
        return args -> {
            for (int i = 0; i < 50; i++) {
                patientRepository.save(
                        new Patient(null, faker.name().firstName(),
                                faker.name().lastName(), faker.internet().emailAddress(),
                                faker.date().birthday(1, 60), faker.bool().bool())
                );
            }
        };
    }

    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService) {
        return args -> {
            securityService.saveUser("mohamed", "1234", "1234");
            securityService.saveUser("Anas", "12345", "12345");
            securityService.saveRole("USER", "");
            securityService.saveRole("ADMIN", "");

            securityService.addRoleToUser("mohamed", "USER");
            securityService.addRoleToUser("mohamed", "ADMIN");
            securityService.addRoleToUser("Anas", "USER");
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}