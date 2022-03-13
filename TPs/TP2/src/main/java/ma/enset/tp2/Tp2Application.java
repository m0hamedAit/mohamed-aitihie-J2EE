package ma.enset.tp2;

import ma.enset.tp2.entities.Patient;
import ma.enset.tp2.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class Tp2Application implements CommandLineRunner { // cette implementation permet de n'executer le code de
                                                        // la methode "run" qu'après que l'app fini le demarrage

    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(Tp2Application.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        //create
        for(int i=0;i<100;i++){
            patientRepository.save(new Patient(null, "A", new Date(), Math.random()>0.5?true:false, (int)(Math.random()*100)));
        }

        //find
        Page<Patient> patients = patientRepository.findAll(PageRequest.of(0,5));  //PageRequest sert a la pagination
        Page<Patient> byMalade = patientRepository.findByMalade(true, PageRequest.of(1,7));
        //page contient d'autre infos (TotalPages, TotalElements, Number, Content (list des Patients de la page))
        patients.forEach(patient -> {
            System.out.println(patient.getNom());
        });

        System.out.println("**************************");
        Patient patient = patientRepository.findById(1L).orElse(null); //.get() return patient or exception if not exist
                                                                            //.orElse(null) return null if not exist
                                                                            //.orElseThrow(()->new RuntimeException("Patient not Found")) return a specific exception if not found
        if(patient!=null){
            System.out.println(patient.getNom());
        }

        //update
        patient.setScore(88);
        patientRepository.save(patient);

        //delete
        patientRepository.deleteById(1L);

    }
}
