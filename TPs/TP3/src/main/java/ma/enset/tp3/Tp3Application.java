package ma.enset.tp3;

import ma.enset.tp3.entities.*;
import ma.enset.tp3.repositories.ConsultationRepository;
import ma.enset.tp3.repositories.MedecinRepository;
import ma.enset.tp3.repositories.PatientRepository;
import ma.enset.tp3.repositories.RendezVousRepository;
import ma.enset.tp3.services.IHospitalService;
import org.aspectj.apache.bcel.classfile.ConstantInteger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class Tp3Application {

    public static void main(String[] args) {
        SpringApplication.run(Tp3Application.class, args);
    }

    @Bean
    CommandLineRunner start(IHospitalService hospitalService, MedecinRepository medecinRepository,
                            PatientRepository patientRepository, RendezVousRepository rendezVousRepository){
        return args -> {
            Stream.of("Mohamed", "Amine", "Ali")
                    .forEach(name->{
                        Patient patient = new Patient();
                        patient.setNom(name);
                        patient.setDateNaissance(new Date());
                        patient.setMalade(Math.random()>0.5?true:false);
                        hospitalService.savePatient(patient);
                    });

            Stream.of("Anass", "Ilham", "Aya")
                    .forEach(name->{
                        Medecin medecin = new Medecin();
                        medecin.setNom(name);
                        medecin.setEmail(name+"@gmail.com");
                        medecin.setSpecialite(Math.random()>0.5?"Generaliste":"Cardio");
                        hospitalService.saveMedecin(medecin);
                    });

            Patient patient = patientRepository.findAll().get(0);
            Medecin medecin = medecinRepository.findByNom("Aya");

            RendezVous rendezVous = new RendezVous(null, new Date(), StatusRDV.PENDING, patient, medecin, null);
            hospitalService.saveRendezVous(rendezVous);

            RendezVous rendezVous1 = rendezVousRepository.findById(1L).orElse(null);

            Consultation consultation = new Consultation(null, new Date(), "All good", rendezVous1);
            hospitalService.saveConsultation(consultation);
        };
    }


}
