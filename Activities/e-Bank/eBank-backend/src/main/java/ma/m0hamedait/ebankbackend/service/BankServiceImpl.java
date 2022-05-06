package ma.m0hamedait.ebankbackend.service;


import lombok.AllArgsConstructor;
import ma.m0hamedait.ebankbackend.entities.Account;
import ma.m0hamedait.ebankbackend.entities.Customer;
import ma.m0hamedait.ebankbackend.entities.Operation;
import ma.m0hamedait.ebankbackend.enums.OperationType;
import ma.m0hamedait.ebankbackend.repositories.AccountRepository;
import ma.m0hamedait.ebankbackend.repositories.CustomerRepository;
import ma.m0hamedait.ebankbackend.repositories.OperationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor

public class BankServiceImpl implements BankService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private OperationRepository operationRepository;


    @Override
    public void saveAccount(Account account) {
        account.setId(UUID.randomUUID().toString());
        accountRepository.save(account);
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account findAccountById(String accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }

    @Override
    public Collection<Operation> findAccountOperations(String accountId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if(account != null)
            return account.getOperations();
        return null;
    }

    @Override
    public void deleteAccount(String accountId) {
        accountRepository.deleteById(accountId);
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findCustomerById(Long costumerId) {
        return customerRepository.findById(costumerId).orElse(null);
    }

    @Override
    public Customer findCustomerByName(String name) {
        return customerRepository.findCustomerByName(name).orElse(null);
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email).orElse(null);
    }

    @Override
    public Collection<Account> findCustomerAccounts(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer != null)
            return customer.getAccounts();
        return null;
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer != null){
            customer.getAccounts().forEach(account -> {
                deleteAccount(account.getId());
            });
            customerRepository.deleteById(customerId);
        }
    }

    @Override
    public void debit(Operation operation) {
        operation.getAccount().setBalance(operation.getAccount().getBalance() + operation.getAmount());
        operationRepository.save(operation);
    }

    @Override
    public boolean credit(Operation operation) {
        if(operation.getAccount().getBalance() >= operation.getAmount()) {
            operation.getAccount().setBalance(operation.getAccount().getBalance() - operation.getAmount());
            operationRepository.save(operation);
            return true;
        }
        return false;
    }

    @Override
    public boolean transfert(Account sender, Account beneficiary, double amount) {  // transfer = debit to beneficiary + credit from sender ?? how to add sender and receiver in each operation
        if(sender.getBalance() >= amount) {
            Date date = new Date();
            Operation operationSend = new Operation();
            operationSend.setOperationDate(date);
            operationSend.setType(OperationType.CREDIT);
            operationSend.setAccount(sender);
            operationSend.setAmount(amount);

            Operation operationReceive = new Operation();
            operationSend.setOperationDate(date);
            operationSend.setType(OperationType.DEBIT);
            operationSend.setAccount(beneficiary);
            operationSend.setAmount(amount);

            credit(operationSend);
            debit(operationReceive);
            return true;
        }
        return false;
    }

    @Override
    public Account consultAccount(String accountId) {   // ??
        return findAccountById(accountId);
    }
}
