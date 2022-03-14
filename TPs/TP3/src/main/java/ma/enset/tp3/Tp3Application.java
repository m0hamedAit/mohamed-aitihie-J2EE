package ma.enset.tp3;

import ma.enset.tp3.entities.Patient;
import ma.enset.tp3.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Tp3Application {
    PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(Tp3Application.class, args);
    }

    // ce code va s'executer au demarrage
    @Bean
    CommandLineRunner start(){
        return args -> {
            
        };
    }


}
