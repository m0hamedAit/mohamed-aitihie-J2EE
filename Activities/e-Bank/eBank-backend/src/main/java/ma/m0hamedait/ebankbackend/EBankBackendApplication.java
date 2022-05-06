package ma.m0hamedait.ebankbackend;

import ma.m0hamedait.ebankbackend.entities.CurrentAccount;
import ma.m0hamedait.ebankbackend.entities.Customer;
import ma.m0hamedait.ebankbackend.entities.Operation;
import ma.m0hamedait.ebankbackend.entities.SavingAccount;
import ma.m0hamedait.ebankbackend.enums.AccountStatus;
import ma.m0hamedait.ebankbackend.enums.OperationType;
import ma.m0hamedait.ebankbackend.repositories.AccountRepository;
import ma.m0hamedait.ebankbackend.repositories.CustomerRepository;
import ma.m0hamedait.ebankbackend.repositories.OperationRepository;
import ma.m0hamedait.ebankbackend.service.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EBankBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EBankBackendApplication.class, args);
    }


    @Bean
    CommandLineRunner start(BankService bankService) {
        return args -> {
            Stream.of("Mohamed", "Anas", "Amine").forEach(name -> {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                bankService.addCustomer(customer);
            });

            bankService.findAllCustomers().forEach(customer -> {
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setOwner(customer);
                currentAccount.setBalance(Math.random() * 100000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setOverDraft(10000);
                currentAccount.setStatus(AccountStatus.CREATED);

                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setOwner(customer);
                savingAccount.setBalance(Math.random() * 100000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setInterestRate(5);
                savingAccount.setStatus(AccountStatus.CREATED);

                bankService.addAccount(currentAccount);
                bankService.addAccount(savingAccount);
            });

            bankService.findAllAccounts().forEach(account -> {
                for(int i=0;i<Math.random()*10;i++){
                    Operation operation = new Operation();
                    operation.setOperationDate(new Date());
                    operation.setAccount(account);
                    operation.setType(Math.random()>0.5? OperationType.CREDIT:OperationType.DEBIT);
                    operation.setAmount(Math.random()*10000);
                    if(operation.getType()==OperationType.CREDIT)
                        bankService.credit(operation);
                    if(operation.getType()==OperationType.DEBIT)
                        bankService.debit(operation);
                }
            });
        };
    }

}
