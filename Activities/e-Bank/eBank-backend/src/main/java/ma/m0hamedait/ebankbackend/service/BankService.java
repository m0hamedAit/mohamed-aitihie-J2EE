package ma.m0hamedait.ebankbackend.service;

import ma.m0hamedait.ebankbackend.dtos.*;
import ma.m0hamedait.ebankbackend.exceptions.AccountNotFoundException;
import ma.m0hamedait.ebankbackend.exceptions.BalanceNotSufficientException;
import ma.m0hamedait.ebankbackend.exceptions.CustomerNotFoundException;

import java.util.Collection;
import java.util.List;

public interface BankService {
    // customer
    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> findAllCustomers();
    CustomerDTO findCustomer(Long id) throws CustomerNotFoundException;
    Collection<AccountDTO> findCustomerAccounts(Long customerId) throws CustomerNotFoundException;
    void deleteCustomer(Long customerId) throws CustomerNotFoundException;
    // account
    CurrentAccountDTO saveCurrentAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingAccountDTO saveSavingAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    AccountDTO getAccount(String accountId) throws AccountNotFoundException;
    List<OperationDTO> findAccountOperations(String accountId) throws AccountNotFoundException;
    List<AccountDTO> findAllAccounts();
    void deleteAccount(String accountId) throws AccountNotFoundException;
    // operations
    void debit(String accountId, String description, double amount)throws AccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId, String description, double amount) throws AccountNotFoundException;
    void transfert(String senderAccountId, String beneficiaryAccountId, double amount) throws AccountNotFoundException, BalanceNotSufficientException;

    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws AccountNotFoundException;
    void deleteOperation(Long idOperation);

    List<CustomerDTO> searchCustomers(String keyword);

    void updateCustomer(Long id, CustomerDTO customerDTO);
}
