package ma.enset.tp5.web;

import lombok.AllArgsConstructor;
import ma.enset.tp5.entities.Patient;
import ma.enset.tp5.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> listPatients(){
        return patientRepository.findAll();
    }

}
