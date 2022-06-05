package ma.m0hamedait.ebankbackend;


import com.github.javafaker.Faker;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import ma.m0hamedait.ebankbackend.dtos.CurrentAccountDTO;
import ma.m0hamedait.ebankbackend.dtos.CustomerDTO;
import ma.m0hamedait.ebankbackend.dtos.SavingAccountDTO;

import ma.m0hamedait.ebankbackend.exceptions.AccountNotFoundException;
import ma.m0hamedait.ebankbackend.exceptions.BalanceNotSufficientException;
import ma.m0hamedait.ebankbackend.exceptions.CustomerNotFoundException;
import ma.m0hamedait.ebankbackend.security.entities.AppUser;
import ma.m0hamedait.ebankbackend.security.service.AccountService;
import ma.m0hamedait.ebankbackend.service.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Stream;

@SecurityScheme(name = "api", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@SpringBootApplication
public class EBankBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EBankBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BankService bankService, AccountService accountService) {
        return args -> {
            Faker faker = new Faker();
            Stream.of("ROLE_ADMIN", "ROLE_USER").forEach(role -> accountService.addRole(role));

            for (int i = 0; i < 10; i++) {
                String username = faker.name().username();
                CustomerDTO customerDTO = new CustomerDTO(null, username, faker.internet().emailAddress());
                AppUser appUser = new AppUser();
                appUser.setUsername(username);
                appUser.setPassword(username);
                bankService.saveCustomer(customerDTO);
                accountService.addUser(appUser);
            }

            AppUser admin = new AppUser();
            admin.setUsername("admin");
            admin.setPassword("admin");
            accountService.addUser(admin);

            accountService.findAll().forEach(appUser -> {
                if(appUser.getUsername().equals("admin"))
                    accountService.addRoleToUser(appUser.getUsername(), "ROLE_ADMIN");
                accountService.addRoleToUser(appUser.getUsername(), "ROLE_USER");
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


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
