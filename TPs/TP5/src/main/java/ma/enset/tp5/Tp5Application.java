package ma.enset.tp5;

import ma.enset.tp5.entities.Patient;
import ma.enset.tp5.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sun.tools.jar.CommandLine;

import java.util.Date;

@SpringBootApplication
public class Tp5Application {

    public static void main(String[] args) {
        SpringApplication.run(Tp5Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient(null, "hassan", new Date(), false, 12));
            patientRepository.save(new Patient(null, "Mohamed", new Date(), true, 312));
            patientRepository.save(new Patient(null, "Yassmin", new Date(), false, 612));
            patientRepository.save(new Patient(null, "Hanae", new Date(), true, 152));

            patientRepository.findAll().forEach(p->{
                System.out.println(p.getName());
            });

        };
    };
}
