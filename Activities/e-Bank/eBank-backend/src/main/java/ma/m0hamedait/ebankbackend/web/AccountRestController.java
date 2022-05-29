package ma.m0hamedait.ebankbackend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.m0hamedait.ebankbackend.dtos.*;
import ma.m0hamedait.ebankbackend.exceptions.AccountNotFoundException;
import ma.m0hamedait.ebankbackend.exceptions.BalanceNotSufficientException;
import ma.m0hamedait.ebankbackend.exceptions.CustomerNotFoundException;
import ma.m0hamedait.ebankbackend.service.BankService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@Slf4j
@CrossOrigin("*")
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
    public void saveAccount(@RequestBody AccountDTO accountDTO) throws CustomerNotFoundException {
       if(accountDTO instanceof SavingAccountDTO) {
           SavingAccountDTO account = (SavingAccountDTO) accountDTO;
           bankService.saveSavingAccount(account.getBalance(), account.getInterestRate(), account.getOwner().getId());
       }
        else {
           CurrentAccountDTO account = (CurrentAccountDTO) accountDTO;
           bankService.saveCurrentAccount(account.getBalance(), account.getOverDraft(), account.getOwner().getId());
       }
    }

    @DeleteMapping("/accounts/{id}")
    public void deleteAccount(@PathVariable String id) throws AccountNotFoundException {
        /*List<OperationDTO> operationsList = bankService.findAccountOperations(id);
        for(OperationDTO operationDTO: operationsList){
            bankService.deleteOperation(operationDTO.getId());
        }*/
        bankService.deleteAccount(id);
    }

    //debit
    @PostMapping("/accounts/debit")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws AccountNotFoundException, BalanceNotSufficientException {
        System.out.println(debitDTO);
        this.bankService.debit(debitDTO.getAccountId(), debitDTO.getDescription(), debitDTO.getAmount());
        return debitDTO;
    }

    @PostMapping("/accounts/credit")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws AccountNotFoundException {
        this.bankService.credit(creditDTO.getAccountId(), creditDTO.getDescription(), creditDTO.getAmount());
        return creditDTO;
    }

    @PostMapping("/accounts/transfer")
    public void transfer(@RequestBody TransferRequestDTO transferRequestDTO) throws AccountNotFoundException, BalanceNotSufficientException {
        this.bankService.transfert(transferRequestDTO.getSenderId(), transferRequestDTO.getRecipientId(), transferRequestDTO.getAmount());

    }

}
