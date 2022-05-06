package ma.m0hamedait.ebankbackend.service;

import ma.m0hamedait.ebankbackend.entities.Account;
import ma.m0hamedait.ebankbackend.entities.Customer;
import ma.m0hamedait.ebankbackend.entities.Operation;

import java.util.Collection;
import java.util.List;

public interface BankService {
    // customer
    void saveCustomer(Customer customer);
    List<Customer> findAllCustomers();
    Customer findCustomerById(Long id);
    Customer findCustomerByName(String name);
    Customer findCustomerByEmail(String email);
    Collection<Account> findCustomerAccounts(Long customerId);
    void deleteCustomer(Long customerId);
    // account
    void saveAccount(Account account);
    List<Account> findAllAccounts();
    Account findAccountById(String accountId);
    Collection<Operation> findAccountOperations(String accountId);
    void deleteAccount(String accountId);
    // operations
    void debit(Operation operation);
    boolean credit(Operation operation);
    boolean transfert(Account sender, Account beneficiary, double amount);
    Account consultAccount(String accountId);
}
