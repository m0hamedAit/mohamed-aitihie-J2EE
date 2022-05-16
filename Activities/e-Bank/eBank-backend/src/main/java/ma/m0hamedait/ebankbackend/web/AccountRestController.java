package ma.m0hamedait.ebankbackend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.m0hamedait.ebankbackend.dtos.AccountDTO;
import ma.m0hamedait.ebankbackend.dtos.AccountHistoryDTO;
import ma.m0hamedait.ebankbackend.dtos.OperationDTO;
import ma.m0hamedait.ebankbackend.entities.Account;
import ma.m0hamedait.ebankbackend.exceptions.AccountNotFoundException;
import ma.m0hamedait.ebankbackend.service.BankService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@Slf4j
public class AccountRestController {
    BankService bankService;

    @GetMapping("/accounts")
    public List<AccountDTO> getAccounts(){
        return bankService.findAllAccounts();
    }

    @GetMapping("/accounts/{accountId}")
    public AccountDTO getAccount(@PathVariable String accountId) throws AccountNotFoundException {
        return bankService.getAccount(accountId);
    }

    @GetMapping("/accounts/{accountId}/operations")
    public List<OperationDTO> getHistory(@PathVariable String accountId) throws AccountNotFoundException {
        return bankService.findAccountOperations(accountId);
    }

    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(@PathVariable String accountId, @RequestParam(name="page",defaultValue = "0") int page, @RequestParam(name="size",defaultValue = "5") int size) throws AccountNotFoundException {
        return bankService.getAccountHistory(accountId, page, size);
    }

    @PostMapping("/accounts")
    public void saveAccounts(@RequestBody Account account){
       /* if(account instanceof SavingAccount)
            bankService.saveSavingAccount(account);
        else
            bankService.saveCurrentAccount(account);*/
    }

    @DeleteMapping("/accounts/{id}")
    public void deleteAccounts(@PathVariable String id) throws AccountNotFoundException {
        bankService.deleteAccount(id);
    }
}
