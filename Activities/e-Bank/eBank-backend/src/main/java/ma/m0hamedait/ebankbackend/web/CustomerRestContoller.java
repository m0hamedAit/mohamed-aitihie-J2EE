package ma.m0hamedait.ebankbackend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.m0hamedait.ebankbackend.dtos.CustomerDTO;
import ma.m0hamedait.ebankbackend.exceptions.CustomerNotFoundException;
import ma.m0hamedait.ebankbackend.service.BankService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CustomerRestContoller {
    private BankService bankService;

    @GetMapping("/customers")
    public List<CustomerDTO> Customers(){
        return bankService.findAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable Long id) throws CustomerNotFoundException {
        return bankService.findCustomer(id);
    }

    @PostMapping("/customers")
    public void saveCustomer(@RequestBody CustomerDTO customerDTO){
        bankService.saveCustomer(customerDTO);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id) throws CustomerNotFoundException {
        bankService.deleteCustomer(id);
    }

}
