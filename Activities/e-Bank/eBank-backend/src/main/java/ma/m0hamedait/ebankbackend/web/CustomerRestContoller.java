package ma.m0hamedait.ebankbackend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.m0hamedait.ebankbackend.dtos.AccountDTO;
import ma.m0hamedait.ebankbackend.dtos.CustomerDTO;
import ma.m0hamedait.ebankbackend.exceptions.AccountNotFoundException;
import ma.m0hamedait.ebankbackend.exceptions.CustomerNotFoundException;
import ma.m0hamedait.ebankbackend.service.BankService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/customers")
@CrossOrigin("*")
public class CustomerRestContoller {
    private BankService bankService;

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<CustomerDTO> getCustomers(){
        return bankService.findAllCustomers();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/search")
    public List<CustomerDTO> searchCustomers(@RequestParam(name="keyword", defaultValue = "") String keyword){
        return bankService.searchCustomers(keyword);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public CustomerDTO getCustomer(@PathVariable Long id) throws CustomerNotFoundException {
        return bankService.findCustomer(id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}/accounts")
    public Collection<AccountDTO> getCustomerAccounts(@PathVariable Long id) throws CustomerNotFoundException {
        return bankService.findCustomerAccounts(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/")
    public void saveCustomer(@RequestBody CustomerDTO customerDTO){
        bankService.saveCustomer(customerDTO);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{id}")
    public void updateCustomer(@PathVariable Long id,
                               @RequestParam(name="name", defaultValue="") String name,
                               @RequestParam(name="email", defaultValue = "") String email) throws CustomerNotFoundException {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(name);
        customerDTO.setEmail(email);
        bankService.updateCustomer(id, customerDTO);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) throws CustomerNotFoundException, AccountNotFoundException {
        bankService.deleteCustomer(id);
    }

}
