package ma.enset.tp3.services;

import ma.enset.tp3.entities.Consultation;
import ma.enset.tp3.entities.Medecin;
import ma.enset.tp3.entities.Patient;
import ma.enset.tp3.entities.RendezVous;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRendezVous(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
}
