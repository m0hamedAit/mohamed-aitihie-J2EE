package ma.enset.tp3.services;

import ma.enset.tp3.entities.Consultation;
import ma.enset.tp3.entities.Medecin;
import ma.enset.tp3.entities.Patient;
import ma.enset.tp3.entities.RendezVous;
import ma.enset.tp3.repositories.ConsultationRepository;
import ma.enset.tp3.repositories.MedecinRepository;
import ma.enset.tp3.repositories.PatientRepository;
import ma.enset.tp3.repositories.RendezVousRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class IHospitalServiceImpl implements IHospitalService {
    private MedecinRepository medecinRepository;
    private PatientRepository patientRepository;
    private RendezVousRepository rendezVousRepository;
    private ConsultationRepository consultationRepository;

    public IHospitalServiceImpl(MedecinRepository medecinRepository, PatientRepository patientRepository,
                                RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository) {
        this.medecinRepository = medecinRepository;
        this.patientRepository = patientRepository;
        this.rendezVousRepository = rendezVousRepository;
        this.consultationRepository = consultationRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        patient.setId(UUID.randomUUID().toString());
        return patientRepository.save(patient);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public RendezVous saveRendezVous(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }
}


