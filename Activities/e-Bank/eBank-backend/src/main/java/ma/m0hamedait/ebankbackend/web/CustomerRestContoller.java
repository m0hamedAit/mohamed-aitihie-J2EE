package ma.m0hamedait.ebankbackend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.m0hamedait.ebankbackend.dtos.AccountDTO;
import ma.m0hamedait.ebankbackend.dtos.CurrentAccountDTO;
import ma.m0hamedait.ebankbackend.dtos.CustomerDTO;
import ma.m0hamedait.ebankbackend.dtos.SavingAccountDTO;
import ma.m0hamedait.ebankbackend.enums.AccountStatus;
import ma.m0hamedait.ebankbackend.exceptions.AccountNotFoundException;
import ma.m0hamedait.ebankbackend.exceptions.CustomerNotFoundException;
import ma.m0hamedait.ebankbackend.service.BankService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")  // autoriser n'import application a envoyer des requete vers cette app
public class CustomerRestContoller {
    private BankService bankService;

    @GetMapping("/customers")
    public List<CustomerDTO> Customers(){
        return bankService.findAllCustomers();
    }

    @GetMapping("/customers/search")
    public List<CustomerDTO> searchCustomers(@RequestParam(name="keyword", defaultValue = "") String keyword){
        return bankService.searchCustomers(keyword);
    }

    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable Long id) throws CustomerNotFoundException {
        return bankService.findCustomer(id);
    }

    @GetMapping("/customers/{id}/accounts")
    public Collection<AccountDTO> getCustomerAccounts(@PathVariable Long id) throws CustomerNotFoundException {
        return bankService.findCustomerAccounts(id);
    }

    @PostMapping("/customers")
    public void saveCustomer(@RequestBody CustomerDTO customerDTO){
        bankService.saveCustomer(customerDTO);
    }

    @PutMapping("/customers/{id}")
    public void updateCustomer(@PathVariable Long id,
                               @RequestParam(name="name", defaultValue="") String name,
                               @RequestParam(name="email", defaultValue = "") String email) throws CustomerNotFoundException {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(name);
        customerDTO.setEmail(email);
        bankService.updateCustomer(id, customerDTO);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id) throws CustomerNotFoundException, AccountNotFoundException {
        /*Collection<AccountDTO> listAccounts = bankService.findCustomerAccounts(id);
        for (AccountDTO account : listAccounts) {
            if(account instanceof SavingAccountDTO)
                bankService.deleteAccount(((SavingAccountDTO) account).getId());
            else
                bankService.deleteAccount(((CurrentAccountDTO) account).getId());
        } */// delete cascade
        bankService.deleteCustomer(id);
    }

}
