package ma.m0hamedait.ebankbackend.web;

import lombok.AllArgsConstructor;
import ma.m0hamedait.ebankbackend.entities.Account;
import ma.m0hamedait.ebankbackend.entities.Customer;
import ma.m0hamedait.ebankbackend.service.BankService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BankRestContoller {
    private BankService bankService;

    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        return bankService.findAllCustomers();
    }

    @GetMapping("/accounts")
    public List<Account> getAccounts(){
        return bankService.findAllAccounts();
    }

    @GetMapping("/custumers/{id}")
    public Customer getCustomer(@PathVariable Long id){
        return bankService.findCustomerById(id);
    }

    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable String id){
        return bankService.findAccountById(id);
    }

    @PostMapping("/customers")
    public void saveCustomer(@RequestBody Customer customer){
        bankService.saveCustomer(customer);
    }

    @PostMapping("/accounts")
    public void saveAccounts(@RequestBody Account account){
        bankService.saveAccount(account);
    }

    @PatchMapping("/customers/{id}")
    public void updateCustomer(@RequestBody Customer customer, @PathVariable Long id){
        customer.setId(id);
        bankService.saveCustomer(customer);
    }

    @PatchMapping("/accounts/{id}")
    public void updateAccount(@RequestBody Account account, @PathVariable String id){
        account.setId(id);
        bankService.saveAccount(account);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id){
        bankService.deleteCustomer(id);
    }

    @DeleteMapping("/accounts/{id}")
    public void deleteAccounts(@PathVariable String id){
        bankService.deleteAccount(id);
    }

}
