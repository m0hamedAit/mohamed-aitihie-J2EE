package ma.m0hamedait.ebankbackend.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.m0hamedait.ebankbackend.dtos.*;
import ma.m0hamedait.ebankbackend.entities.*;
import ma.m0hamedait.ebankbackend.enums.OperationType;
import ma.m0hamedait.ebankbackend.exceptions.AccountNotFoundException;
import ma.m0hamedait.ebankbackend.exceptions.BalanceNotSufficientException;
import ma.m0hamedait.ebankbackend.exceptions.CustomerNotFoundException;
import ma.m0hamedait.ebankbackend.mappers.MapperImpl;
import ma.m0hamedait.ebankbackend.repositories.AccountRepository;
import ma.m0hamedait.ebankbackend.repositories.CustomerRepository;
import ma.m0hamedait.ebankbackend.repositories.OperationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j  // for logs
public class BankServiceImpl implements BankService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private OperationRepository operationRepository;
    private MapperImpl mapper;


    @Override
    public CurrentAccountDTO saveCurrentAccount(double initialBalance,
                                                double overDraft, Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer==null)
            throw new CustomerNotFoundException("Customer not found");
        CurrentAccount account = new CurrentAccount();
        account.setId(UUID.randomUUID().toString());
        account.setBalance(initialBalance);
        account.setOwner(customer);
        account.setCreatedAt(new Date());
        account.setOverDraft(overDraft);
        log.info("Current Account saved");
        CurrentAccount savedAccount = accountRepository.save(account);
        return mapper.fromCurrentAccount(savedAccount);
    }

    @Override
    public SavingAccountDTO saveSavingAccount(double initialBalance,
                                              double interestRate, Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer==null)
            throw new CustomerNotFoundException("Customer not found");
        SavingAccount account = new SavingAccount();
        account.setId(UUID.randomUUID().toString());
        account.setBalance(initialBalance);
        account.setOwner(customer);
        account.setCreatedAt(new Date());
        account.setInterestRate(interestRate);
        log.info("Saving Account saved");
        SavingAccount savedAccount = accountRepository.save(account);
        return mapper.fromSavingAccount(savedAccount);
    }


    @Override
    public AccountDTO getAccount(String accountId) throws AccountNotFoundException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(()-> new AccountNotFoundException("Account not found"));

        return mapper.fromAccount(account);
    }

    @Override
    public List<OperationDTO> findAccountOperations(String accountId) throws AccountNotFoundException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(()->new AccountNotFoundException("Account not found"));

        return account.getOperations().stream()
                .map(operation -> mapper.fromOperation(operation)).collect(Collectors.toList());

    }

    @Override
    public List<AccountDTO> findAllAccounts() {
        return accountRepository.findAll().stream()
                .map(account -> mapper.fromAccount(account)).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(String accountId) throws AccountNotFoundException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(()-> new AccountNotFoundException("Account not found"));
        accountRepository.deleteById(accountId);
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        log.info("saving new customer");
        Customer customer = mapper.fromCustomerDTO(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return mapper.fromCustomer(savedCustomer);
    }

    @Override
    public List<CustomerDTO> findAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> mapper.fromCustomer(customer))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findCustomer(Long costumerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(costumerId)
                .orElseThrow(()->new CustomerNotFoundException("Customer not found"));
        return mapper.fromCustomer(customer);

    }

    @Override
    public Collection<AccountDTO> findCustomerAccounts(Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()->new CustomerNotFoundException("Customer not found"));

        return customer.getAccounts().stream().map(account -> mapper.fromAccount(account)).collect(Collectors.toList());
    }

    @Override
    public void deleteCustomer(Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()->new CustomerNotFoundException("Customer not found"));

        customerRepository.deleteById(customerId);
    }

    @Override
    public void debit(String accountId, String description, double amount)
            throws AccountNotFoundException, BalanceNotSufficientException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(()-> new AccountNotFoundException("Account not found"));
        if(account.getBalance()<amount)
            throw new BalanceNotSufficientException("Balance not sufficient");
        Operation operation = new Operation();
        operation.setType(OperationType.DEBIT);
        operation.setAmount(amount);
        operation.setOperationDate(new Date());
        operation.setAccount(account);
        operation.setDescription(description);
        operationRepository.save(operation);
        account.setBalance(account.getBalance()-amount);
        accountRepository.save(account);
    }

    @Override
    public void credit(String accountId, String description, double amount) throws AccountNotFoundException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(()-> new AccountNotFoundException("Account not found"));

        Operation operation = new Operation();
        operation.setType(OperationType.CREDIT);
        operation.setAmount(amount);
        operation.setOperationDate(new Date());
        operation.setAccount(account);
        operation.setDescription(description);
        operationRepository.save(operation);
        account.setBalance(account.getBalance()+amount);
        accountRepository.save(account);
    }

    @Override
    public void transfert(String senderAccountId,
                          String recipientAccountId, double amount)
            throws AccountNotFoundException, BalanceNotSufficientException {  // transfer = debit to beneficiary + credit from sender ?? how to add sender and receiver in each operation
        debit(recipientAccountId,"Transfer from"+senderAccountId, amount);
        credit(senderAccountId,"Transfer to"+recipientAccountId, amount);
    }

    @Override
    public AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws AccountNotFoundException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(()-> new AccountNotFoundException("Account not found"));
        Page<Operation> accountOperations = operationRepository.findByAccountIdOrderByOperationDateDesc(accountId, PageRequest.of(page, size));
        AccountHistoryDTO accountHistoryDTO = new AccountHistoryDTO();
        List<OperationDTO> operationDTOS = accountOperations.getContent().stream()
                .map(op->mapper.fromOperation(op)).collect(Collectors.toList());
        accountHistoryDTO.setOperationDTOS(operationDTOS);
        accountHistoryDTO.setId(account.getId());
        accountHistoryDTO.setBalance(account.getBalance());
        accountHistoryDTO.setCurrentPage(page);
        accountHistoryDTO.setPageSize(size);
        accountHistoryDTO.setTotalPages(accountOperations.getTotalPages());
        return accountHistoryDTO;
    }

    @Override
    public void deleteOperation(Long idOperation) {
        Operation operation = operationRepository.findById(idOperation)
                .orElseThrow(()->new RuntimeException("Operation not found !"));
        operationRepository.delete(operation);
    }

    @Override
    public List<CustomerDTO> searchCustomers(String keyword) {
        List<Customer> customers = customerRepository.searchCustomers(keyword);
        return customers.stream().map(customer -> mapper.fromCustomer(customer)).collect(Collectors.toList());
    }

    @Override
    public void updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Customer not found !"));
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customerRepository.save(customer);
    }
}
