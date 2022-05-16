package ma.m0hamedait.ebankbackend;


import ma.m0hamedait.ebankbackend.dtos.CurrentAccountDTO;
import ma.m0hamedait.ebankbackend.dtos.CustomerDTO;
import ma.m0hamedait.ebankbackend.dtos.SavingAccountDTO;
import ma.m0hamedait.ebankbackend.entities.Account;
import ma.m0hamedait.ebankbackend.entities.Customer;
import ma.m0hamedait.ebankbackend.exceptions.AccountNotFoundException;
import ma.m0hamedait.ebankbackend.exceptions.BalanceNotSufficientException;
import ma.m0hamedait.ebankbackend.exceptions.CustomerNotFoundException;
import ma.m0hamedait.ebankbackend.service.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class EBankBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EBankBackendApplication.class, args);
    }


    //@Bean
    CommandLineRunner start(BankService bankService) {
        return args -> {
            Stream.of("Mohamed", "Anas", "Amine").forEach(name -> {
                System.out.println(name);

                CustomerDTO customer = new CustomerDTO();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                bankService.saveCustomer(customer);
            });

            bankService.findAllCustomers().forEach(customer -> {
                try {
                    bankService.saveCurrentAccount(Math.random() * 100000, 10000, customer.getId());
                    bankService.saveSavingAccount(Math.random() * 100000,5, customer.getId());
                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }
            });

            bankService.findAllAccounts().forEach(account -> {
                String accountId;
                if(account instanceof SavingAccountDTO)
                    accountId = ((SavingAccountDTO)account).getId();
                else
                    accountId = ((CurrentAccountDTO)account).getId();

                for(int i=0;i<Math.random()*10;i++){
                    try {
                        bankService.credit(accountId, "Credit", Math.random()*10000);
                        bankService.debit(accountId, "Debit", Math.random()*10000);
                    } catch (AccountNotFoundException | BalanceNotSufficientException e) {
                        e.printStackTrace();
                    }
                }
            });
        };
    }

}
