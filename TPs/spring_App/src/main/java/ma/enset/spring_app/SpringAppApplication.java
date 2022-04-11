package ma.enset.spring_app;

import ma.enset.spring_app.entities.Patient;
import ma.enset.spring_app.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class SpringAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAppApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            /*patientRepository.save(new Patient(null, "hassan", "Ajkh", "hassan@email.com" , new Date(), false));
            patientRepository.save(new Patient(null, "Mohamed", "Bqsd", "mohamed@email.com" , new Date(), true));
            patientRepository.save(new Patient(null, "Yassmin", "Csdfs", "yassmine@email.com" , new Date(), false));
            patientRepository.save(new Patient(null, "Hanae", "Dqsdf", "hanae@email.com" ,  new Date(), true));

            patientRepository.findAll().forEach(p->{
                System.out.println(p.getFirstName());
            });*/
        };
    }
}
