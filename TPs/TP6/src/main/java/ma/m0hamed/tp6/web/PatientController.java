package ma.m0hamed.tp6.web;

import lombok.AllArgsConstructor;
import ma.m0hamed.tp6.entities.Patient;
import ma.m0hamed.tp6.repositories.PatientRepository;
import ma.m0hamed.tp6.services.HopitalService;
import ma.m0hamed.tp6.services.HopitalServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@Controller
@AllArgsConstructor
public class PatientController {

    private HopitalService hopitalService;

    @GetMapping("/home")
    public String home(Model model,
                       @RequestParam(name="page", defaultValue ="0")int page,
                       @RequestParam(name="size", defaultValue = "10")int size,
                       @RequestParam(name="keyword", defaultValue="")String keyword){
        Page<Patient> patients = hopitalService
                .getPatientByName(keyword,PageRequest.of(page, size));
        model.addAttribute("patients", patients.getContent());
        model.addAttribute("pages", new int[patients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword", keyword);
        return "home";
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/home";
    }

    @GetMapping("/admin/delete")
    public String delete(Long id, String keyword, int page){
        hopitalService.deletePatient(id);
        return "redirect:/home?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/new")
    public String formPatients(Model model, String keyword, int page){
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("patient", new Patient());
        return "addPatient";
    }

    @PostMapping(path="/admin/save")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult,
                       @RequestParam(name="keyword", defaultValue = "")String keyword,
                       @RequestParam(name="page",defaultValue = "0") int page){
        if(bindingResult.hasErrors()) return "addPatient";
        hopitalService.savePatient(patient);
        return "redirect:/home?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/edit")
    public String editPatient(Model model, Long id, String keyword, int page){
        Patient patient = hopitalService.getPatient(id);
        if(patient==null) throw new RuntimeException("Patient doesn't exist !!");
        model.addAttribute("patient", patient);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editPatient";
    }

}
