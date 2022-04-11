package ma.enset.tp5.web;

import lombok.AllArgsConstructor;
import ma.enset.tp5.entities.Patient;
import ma.enset.tp5.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;

    @GetMapping(path="/index")
    public String patients(Model model,
                           @RequestParam(name="page", defaultValue = "0") int page,
                           @RequestParam(name="size",defaultValue = "5") int size,
                           @RequestParam(name="keyword",defaultValue = "") String keyword){
        Page<Patient> pagePatient = patientRepository.findByNameContains(keyword, PageRequest.of(page, size));
        model.addAttribute("patientslist", pagePatient.getContent());
        model.addAttribute("pages", new int[pagePatient.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "patients"; //vue
    }

    @GetMapping("/delete")
    public String delete(Long id, String keyword, int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&kewyord="+keyword;
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/index";
    }

    @GetMapping("/patients/new")
    public String patientForm(Model model){
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        return "patient_form";
    }

    @PostMapping("/patients/save")
    public String savePatient(@ModelAttribute("patient") Patient patient){
        patientRepository.save(patient);
        return "redirect:/patients";
    }

    @GetMapping("/patients/edit/{id}")
    public String updatePatientForm(@PathVariable(value="id") Long id,
                                Model model){
        model.addAttribute("patient", patientRepository.getById(id));
        return "update_patient";
    }

    @PostMapping("/patients/{id}")
    public String updatePatient(Long id,
                                @ModelAttribute("patient") Patient patient,
                                Model model){
        Patient oldPatient = patientRepository.getById(id);
        oldPatient.setName(patient.getName());
        oldPatient.setBirthDate(patient.getBirthDate());
        oldPatient.setScore(patient.getScore());

        patientRepository.save(oldPatient);

        return "redirect:/patients";
    }

    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> listPatients(){
        return patientRepository.findAll();
    }

}
